// https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php
// https://algs4.cs.princeton.edu/22mergesort/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
// import edu.princeton.cs.algs4.StdDraw;

public class FastCollinearPoints {
    private LineSegment[] colinearSegments = {};
    private int colinearSegmentsCount = 0;

    public FastCollinearPoints(Point[] pointsInput) {    // finds all line segments containing 4 or more points
        if ( pointsInput == null ) throw new java.lang.IllegalArgumentException("null points[]");
        Point[] points = new Point[pointsInput.length];
        for (int i = 0; i < pointsInput.length; i++){
            if (pointsInput[i] == null ) throw new java.lang.IllegalArgumentException("null point at " + i);
            points[i] = pointsInput[i];
        }
        
        Point origin;
        Point endPoint = null;
        int length;
        boolean addFlag;

        for (int p = 0; p < points.length; p++) {
            Arrays.sort(points);
            origin = points[p];

            if (p == 0){ // check for repeated points
                for (int i = 1; i < points.length; i++){
                    if (points[i].compareTo(points[i-1]) == 0) {
                        throw new IllegalArgumentException("repeated point");
                    }
                }
            }
            
            // For each other point q, determine the slope it makes with p.
            // Sort the points according to the slopes they makes with p
            Arrays.sort(points, origin.slopeOrder());

            // Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.
            for (int q = 1; q < points.length; q++) {
                // StdOut.println(points[q]);
                length = 2; // origin + first element of group
                while ((q < points.length) && (origin.slopeTo(points[q]) == origin.slopeTo(points[q-1]))) {
                    // StdOut.println(points[q]);
                    endPoint = points[q];
                    length++;
                    q++;
                }
                if (length >= 4) {
                    // check if origin is local minimum and endPoint is local max to avoid double counting
                    addFlag = true;
                    for ( int i = q - length + 1 ; i < q; i++){
                        if (origin.compareTo(points[i]) > 0) addFlag = false;
                        if (endPoint.compareTo(points[i]) < 0) addFlag = false;
                    }
                    if (addFlag) {
                        // colinearSegments[colinearSegmentsCount++] = new LineSegment(origin, endPoint);
                        // if ( colinearSegmentsCount != 0) {
                        colinearSegments = Arrays.copyOf(colinearSegments, colinearSegments.length+1);
                        // }
                        colinearSegments[colinearSegmentsCount++] = new LineSegment(origin, endPoint);
                    }
                }
            }
        }

    }
    public int numberOfSegments() {        // the number of line segments
        return colinearSegmentsCount;
    }

    public LineSegment[] segments() {               // the line segments
        return colinearSegments.clone();
    }
 


    public static void main(String[] args) {
        // read the n points from a file
        // In in = new In(args[0]);
        // int n = in.readInt();
        // Point[] points = new Point[n];
        // for (int i = 0; i < n; i++) {
        //     int x = in.readInt();
        //     int y = in.readInt();
        //     points[i] = new Point(x, y);
        // }

        Point[] points = new Point[5];
        points[0] = new Point(1,4);
        points[1] = new Point(7,8);
        points[2] = new Point(7,7);
        points[3] = new Point(0,1);
        points[4] = new Point(7,5);
        // points[5] = new Point(21,1);
        // points[6] = new Point(20,1);
        // points[7] = new Point(0,100);
        // points[8] = new Point(0,8);
        // points[9] = new Point(6,6);

        // draw the points
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // for (Point p : points) {
        //     p.draw();
        // }
        // StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        // StdOut.println("done");

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            // segment.draw();
        }
        // StdDraw.show();
}
}