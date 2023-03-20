    package org.ru.nsu.mikhalev.task3.controller;

    import org.ru.nsu.mikhalev.task3.model.CheckMove;
    import org.ru.nsu.mikhalev.task3.model.Context;
    import org.ru.nsu.mikhalev.task3.model.TetrisShape;
    import org.ru.nsu.mikhalev.task3.model.shape.*;
    import org.ru.nsu.mikhalev.task3.view.DrawDetails;
    import org.ru.nsu.mikhalev.task3.view.DrawRectangle;
    import org.ru.nsu.mikhalev.task3.view.FieldPanel;
    import org.ru.nsu.mikhalev.task3.view.HorizontalGradientButton;
    import org.ru.nsu.mikhalev.task3.view.SetColor;

    import javax.imageio.ImageIO;
    import javax.swing.*;
    import java.awt.*;
    import java.io.File;
    import java.io.IOException;
    import java.util.Random;

    public class GameArea extends JPanel {
        private Random random;
        private Color[][] placedShape = new Color[Context.getHEIGHT()][Context.getWIDTH()];
        private TetrisShape shape;
        private  TetrisShape[] shapes;
        private Image backgroundImage;
        private Integer pointPlayer = 0;

        JButton buttonMenu = new HorizontalGradientButton("Rules!",
                                                          250,
                                                          450,
                                                          SetColor.GOLD_START.get(),
                                                          SetColor.GOLD_END.get());

        public GameArea()  {
            setBounds(getBounds());

            random = new Random();
            shapes = new TetrisShape[] {
                        new SmashBoy(),
                        new Ricky(),
                        new Hero(),
                        new TeeWee(),
                        new Cleveland()
                    };
            try {
                backgroundImage = ImageIO.read(new File(Context.getPATH_RESOURCES() + "PanelGameArea.jpg"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            add(buttonMenu);
            spawnShape();
        }
        public int getPointPlayer() { return pointPlayer; }
        public void spawnShape() {
            shape = shapes[random.nextInt(shapes.length)];
            shape.rotate();
            shape.setColor();
            shape.spawn();
        }
        private void clearLine(int row) {
            for (int i = 0; i < Context.getWIDTH(); ++i) {
                placedShape[row][i] = null;
            }
        }
        private void shiftDown(int curRow) {
            for(int row = curRow; row > 0; --row) {
                for(int column = 0; column < Context.getWIDTH(); ++column) {
                    placedShape[row][column] = placedShape[row-1][column];
                }
            }
        }
        public boolean isBlockOutOfBounds() {
            return shape.getY() <= 0;
        }
        public void clearLines() {
            int cntStatusField;
            for(int row = 0; row < Context.getHEIGHT(); ++row) {
                cntStatusField = 0;
                for(int column = 0; column < Context.getWIDTH(); ++column) {
                    if(placedShape[row][column] != null) ++cntStatusField;
                }
                if(cntStatusField == Context.getNUMBER_CUBES_ROW()) {
                    pointPlayer += 100;
                    clearLine(row);
                    shiftDown(row);
                    clearLine(0);
                    repaint();
                }
            }
        }
        public boolean IsMoveShapeDown() {
            if(!CheckMove.checkBarrier(shape, placedShape)) {
                moveShapeToBackGround();
                clearLines();
                return false;
            }
            shape.moveDown();
            repaint();
            return true;
        }
        public void moveShapeDown() {
            while(!CheckMove.checkBarrier(shape, placedShape)) {
                shape.moveDown();
            }
            repaint();
        }
        public void moveShapeUp() {
            shape.moveUp();
            repaint();
        }
        public void moveShapeLeft() {
            if(!CheckMove.checkMoveLeftShape(shape, placedShape)) return;
            shape.moveLeft();
            repaint();
        }
        public void moveShapeRight() {
            if(!CheckMove.checkMoveRightShape(shape, placedShape)) return;
            shape.moveRight();
            repaint();
        }
        public void moveShapeRotate() {
            if(!CheckMove.checkMoveRotateShape(shape, placedShape)) return;
            shape.nextRotation();
            repaint();
        }
        public void moveShapeToBackGround() {
            pointPlayer += 10;
            int h = shape.getHeight();
            int w = shape.getWidth();
            for(int y = 0; y < h; ++y) {
                for(int x = 0; x < w; ++x) {
                    if (shape.IsShape(x, y))
                        placedShape[y + shape.getY()][x + shape.getX()] = shape.getColor();
                }
            }
        }
        @Override
        protected void paintComponent(Graphics graphics) {
            if (backgroundImage != null) {
                graphics.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }

            Font font = new Font("Comic", Font.BOLD, 80);
            graphics.setFont(font);
            graphics.setColor(new Color(88, 114, 140));
            graphics.drawString("POINT", 1050, 80);

            graphics.drawString (pointPlayer.toString(), 1200, 160);

            FieldPanel.drawGridSquare(graphics);
            DrawRectangle r = new DrawRectangle(Context.getOFFSET_TABLE_X (),
                                                Context.getOFFSET_TABLE_Y(),
                                             500,
                                             800);
            add(r);
            DrawDetails.drawDetails(graphics, shape);
            FieldPanel.drawBackGround(graphics, placedShape);
        }
    }