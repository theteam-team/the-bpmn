package com.theteam.bpmn.design.dnode;


import javafx.geometry.Point2D;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;


/**
 * Draw a line between nodes
 * Lines could be drawn as straight line or as a cubic curve
 * Lines aren't considered design nodes (dnodes) as such
 * It doesn't inherit from dnode
 * Should be move to a new package
 */
public class DLine extends Path
{

    double startX;
    double startY;
    double endX;
    double endY;
    double controlX1;
    double controlY1;
    double controlX2;
    double controlY2;
    double arrowHeadSize;

    // Assign them
    private DNode from = null;
    private DNode to = null;

    private MoveTo moveTo = null;
    private CubicCurveTo cubicCurveTo = null;
    private LineTo lineTo = null;

    private static final double defaultArrowHeadSize = 3.0;
    
    public DLine(double startX, double startY, double endX, double endY,
     double controlX1, double controlY1 ,  double controlX2, double controlY2, double arrowHeadSize){

        super();

        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.controlX1 = controlX1;
        this.controlY1 = controlY1;
        this.controlX2 = controlX2;
        this.controlY2 = controlY2;
        this.arrowHeadSize = arrowHeadSize;
        
        // Stop[] stops = new Stop[] { new Stop(0, Color.VIOLET), new Stop(1, Color.DARKCYAN)};
        // LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

        setStroke(Color.GOLD);
        setStrokeWidth(2);
        setFill(null);

        setEffect(new Bloom());
        
        moveTo = new MoveTo(startX, startY);
        cubicCurveTo = new CubicCurveTo(controlX1, controlY1, controlX2, controlY2, endX, endY);
        lineTo = new LineTo(endX, endY);

        getElements().add(moveTo);


        if(Math.abs(endY - startY) > 40) 
        {
            // CubicCurve
            getElements().add(cubicCurveTo);
            
            // point1
            double x1 = endX - 4;
            double y1 = endY - 2.5;
            // point2
            double x2 = endX - 4;
            double y2 = endY + 2.5;
            
            getElements().add(new LineTo(x1, y1));
            getElements().add(new MoveTo(endX, endY));
            getElements().add(new LineTo(x2, y2));

        }

        else
        {

            // Line
            getElements().add(lineTo);
            
            // ArrowHead
            double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
            double sin = Math.sin(angle);
            double cos = Math.cos(angle);
            // point1
            double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
            double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
            // point2
            double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
            double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
            
            getElements().add(new LineTo(x1, y1));
            getElements().add(new MoveTo(endX, endY));
            getElements().add(new LineTo(x2, y2));

        }
        
        
    }
    
    public DLine(double startX, double startY, double endX, double endY,
    double controlX1, double controlY1 ,  double controlX2, double controlY2){

        this(startX, startY, endX, endY,
                controlX1, controlY1, controlX2, controlY2, defaultArrowHeadSize);
    }

    public DLine(DNode nodeFrom, DNode nodeTo)
    {
        this(   nodeFrom.getX()+NodeConstants.nodeSize,
                nodeFrom.getY()+NodeConstants.nodeSize/2,
                nodeTo.getX(),
                nodeTo.getY()+NodeConstants.nodeSize/2,
                nodeFrom.getX()+NodeConstants.nodeSize+100,
                nodeFrom.getY()+NodeConstants.nodeSize/2,
                nodeTo.getX()-100,
                nodeTo.getY()+NodeConstants.nodeSize/2);

        from = nodeFrom;
        to = nodeTo;

    }

    public DNode getNodeFrom() { return from; }
    public DNode getNodeTo() { return to; }


    public void changeLineStart(DNode start)
    {
        getElements().clear();

        this.startX = start.getX()+NodeConstants.nodeSize;
        this.startY = start.getY()+NodeConstants.nodeSize/2;
        
        this.controlX1 = start.getX()+NodeConstants.nodeSize+100;
        this.controlY1 = start.getY()+NodeConstants.nodeSize/2;
        
        moveTo = new MoveTo(startX, startY);
        cubicCurveTo = new CubicCurveTo(controlX1, controlY1, controlX2, controlY2, endX, endY);
        lineTo = new LineTo(endX, endY);

        getElements().add(moveTo);


        if(Math.abs(endY - startY) > 40) 
        {
            // CubicCurve
            getElements().add(cubicCurveTo);
            
            // point1
            double x1 = endX - 4;
            double y1 = endY - 2.5;
            // point2
            double x2 = endX - 4;
            double y2 = endY + 2.5;
            
            getElements().add(new LineTo(x1, y1));
            getElements().add(new MoveTo(endX, endY));
            getElements().add(new LineTo(x2, y2));

        }

        else
        {

            // Line
            getElements().add(lineTo);
            
            // ArrowHead
            double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
            double sin = Math.sin(angle);
            double cos = Math.cos(angle);
            // point1
            double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
            double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
            // point2
            double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
            double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
            
            getElements().add(new LineTo(x1, y1));
            getElements().add(new MoveTo(endX, endY));
            getElements().add(new LineTo(x2, y2));

        }
        
        
        
    }

    public void changeLineEnd(DNode end)
    {
        getElements().clear();
        
        this.endX = end.getX();
        this.endY = end.getY()+NodeConstants.nodeSize/2;
        this.controlX2 = end.getX()-100;
        this.controlY2 = end.getY()+NodeConstants.nodeSize/2;
        
        moveTo = new MoveTo(startX, startY);
        cubicCurveTo = new CubicCurveTo(controlX1, controlY1, controlX2, controlY2, endX, endY);
        lineTo = new LineTo(endX, endY);

        getElements().add(moveTo);


        if(Math.abs(endY - startY) > 40) 
        {
            // CubicCurve
            getElements().add(cubicCurveTo);
            
            // point1
            double x1 = endX - 4;
            double y1 = endY - 2.5;
            // point2
            double x2 = endX - 4;
            double y2 = endY + 2.5;
            
            getElements().add(new LineTo(x1, y1));
            getElements().add(new MoveTo(endX, endY));
            getElements().add(new LineTo(x2, y2));

        }

        else
        {

            // Line
            getElements().add(lineTo);
            
            // ArrowHead
            double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
            double sin = Math.sin(angle);
            double cos = Math.cos(angle);
            // point1
            double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
            double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
            // point2
            double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
            double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
            
            getElements().add(new LineTo(x1, y1));
            getElements().add(new MoveTo(endX, endY));
            getElements().add(new LineTo(x2, y2));

        }
        
        
    }


    private void createLine(DNode from, DNode to)
    {
        Line line = new Line(from.getX()+100, from.getY()+50, to.getX(), to.getY()+50);

        CubicCurve curve = new CubicCurve(  from.getX()+100,
                                            from.getY()+50,
                                            from.getX()+300,
                                            from.getY()+50,
                                            to.getX()-200,
                                            to.getY()+50,
                                            to.getX(),
                                            to.getY()+50);
        curve.setStroke(Color.BLACK);
        curve.setStrokeWidth(5);
        curve.setFill( null);

        double size=Math.max(curve.getBoundsInLocal().getWidth(),
        curve.getBoundsInLocal().getHeight());
        double scale=size/4d;

        Point2D ori=eval(curve,1);
        Point2D tan=evalDt(curve,1).normalize().multiply(scale);
        Path arrowEnd=new Path();
        arrowEnd.getElements().add(new MoveTo(ori.getX()-0.2*tan.getX()-0.2*tan.getY(),
                                            ori.getY()-0.2*tan.getY()+0.2*tan.getX()));
        arrowEnd.getElements().add(new LineTo(ori.getX(), ori.getY()));
        arrowEnd.getElements().add(new LineTo(ori.getX()-0.2*tan.getX()+0.2*tan.getY(),
                                            ori.getY()-0.2*tan.getY()-0.2*tan.getX()));


        //drawArea.getChildren().addAll(line, arrowEnd);
    }

    private void createCubicLine(DNode from, DNode to)
    {

        CubicCurve curve = new CubicCurve(  from.getX()+100,
                                            from.getY()+50,
                                            from.getX()+300,
                                            from.getY()+50,
                                            to.getX()-200,
                                            to.getY()+50,
                                            to.getX(),
                                            to.getY()+50);
        curve.setStroke(Color.BLACK);
        curve.setStrokeWidth(5);
        curve.setFill( null);

        double size=Math.max(curve.getBoundsInLocal().getWidth(),
        curve.getBoundsInLocal().getHeight());
        double scale=size/4d;

        /*
        Point2D ori=eval(curve,0);
        Point2D tan=evalDt(curve,0).normalize().multiply(scale);
        Path arrowIni=new Path();
        arrowIni.getElements().add(new MoveTo(ori.getX()+0.2*tan.getX()-0.2*tan.getY(),
                                            ori.getY()+0.2*tan.getY()+0.2*tan.getX()));
        arrowIni.getElements().add(new LineTo(ori.getX(), ori.getY()));
        arrowIni.getElements().add(new LineTo(ori.getX()+0.2*tan.getX()+0.2*tan.getY(),
                                            ori.getY()+0.2*tan.getY()-0.2*tan.getX()));
        */

        //Arrow ar = new Arrow(from.getX()+50, from.getY()+25, to.getX(), to.getY()+25);

        //Point2D ori=eval(curve,1);
        //Point2D tan=evalDt(curve,1).normalize().multiply(scale);
        //Path arrowEnd=new Path();
        //arrowEnd.getElements().add(new MoveTo(ori.getX()-0.2*tan.getX()-0.2*tan.getY(),
        //                                    ori.getY()-0.2*tan.getY()+0.2*tan.getX()));
        //arrowEnd.getElements().add(new LineTo(ori.getX(), ori.getY()));
        //arrowEnd.getElements().add(new LineTo(ori.getX()-0.2*tan.getX()+0.2*tan.getY(),
        //                                    ori.getY()-0.2*tan.getY()-0.2*tan.getX()));

        //drawArea.getChildren().addAll(curve, ar);
    }

    /**
     * Evaluate the cubic curve at a parameter 0<=t<=1, returns a Point2D
     * @param c the CubicCurve 
     * @param t param between 0 and 1
     * @return a Point2D 
     */
    private Point2D eval(CubicCurve c, float t){
        Point2D p=new Point2D(Math.pow(1-t,3)*c.getStartX()+
                3*t*Math.pow(1-t,2)*c.getControlX1()+
                3*(1-t)*t*t*c.getControlX2()+
                Math.pow(t, 3)*c.getEndX(),
                Math.pow(1-t,3)*c.getStartY()+
                3*t*Math.pow(1-t, 2)*c.getControlY1()+
                3*(1-t)*t*t*c.getControlY2()+
                Math.pow(t, 3)*c.getEndY());
        return p;
    }

    /**
     * Evaluate the tangent of the cubic curve at a parameter 0<=t<=1, returns a Point2D
     * @param c the CubicCurve 
     * @param t param between 0 and 1
     * @return a Point2D 
     */
    private Point2D evalDt(CubicCurve c, float t){
        Point2D p=new Point2D(-3*Math.pow(1-t,2)*c.getStartX()+
                3*(Math.pow(1-t, 2)-2*t*(1-t))*c.getControlX1()+
                3*((1-t)*2*t-t*t)*c.getControlX2()+
                3*Math.pow(t, 2)*c.getEndX(),
                -3*Math.pow(1-t,2)*c.getStartY()+
                3*(Math.pow(1-t, 2)-2*t*(1-t))*c.getControlY1()+
                3*((1-t)*2*t-t*t)*c.getControlY2()+
                3*Math.pow(t, 2)*c.getEndY());
        return p;
    }
}