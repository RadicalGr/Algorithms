// https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php
// https://algs4.cs.princeton.edu/22mergesort/


import java.util.Arrays;
import edu.princeton.cs.algs4.In;
// import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;


public class BruteCollinearPoints {
    private LineSegment[] colinearSegments = {};
    private int colinearSegmentsCount = 0;

    /* To check whether the 4 points p, q, r, and s are collinear, check whether the 
    three slopes between p and q, between p and r, and between p and s are all equal */
    public BruteCollinearPoints(Point[] pointsInput) {   // finds all line segments containing 4 points
        if ( pointsInput == null ) throw new java.lang.IllegalArgumentException("null points[]");
        Point[] points = new Point[pointsInput.length];
        for (int i = 0; i < pointsInput.length; i++){
            if (pointsInput[i] == null ) throw new java.lang.IllegalArgumentException("null point at " + i);
            points[i] = pointsInput[i];
        }

        Double s1;
        Double s2;
        Double s3;

        Arrays.sort(points);
        for (int p = 0; p < points.length; p++) {
            if ( points[p] == null ) throw new IllegalArgumentException(); // point is null

            for (int q = p+1; q < points.length; q++) {
                if ( (p != q) && (points[q] == points[p]) ) throw new IllegalArgumentException(); // repeated point
                if ( q == p ) continue;
                s1 = points[p].slopeTo(points[q]);

                for (int r = q+1; r < points.length; r++) {
                    if ( r == q || r == p) continue; // do not consider same point twice
                    s2 = points[p].slopeTo(points[r]);
                    if ( !s2.equals(s1) ) continue;

                    for (int s = r+1; s < points.length; s++) {
                        if ( s == r || s == q || s == p ) continue;
                        s3 = points[p].slopeTo(points[s]);
                        // StdOut.println(points[p]+", "+points[q]+", "+points[r]+", "+points[s]);
                        // StdOut.println(s1+", "+s2+", "+s3);

                        if ( s1.equals(s2) && s1.equals(s3) ){ // points are collinear
                            if ( points[p].compareTo(points[q]) < 0 && 
                                 points[q].compareTo(points[r]) < 0 && 
                                 points[r].compareTo(points[s]) < 0 ) { // allow only one combination
                                // colinearSegments[colinearSegmentsCount++] = new LineSegment(points[p], points[s]);
                                // if ( colinearSegmentsCount != 0) {
                                colinearSegments = Arrays.copyOf(colinearSegments, colinearSegments.length+1);
                                // }
                                colinearSegments[colinearSegmentsCount++] = new LineSegment(points[p], points[s]);
                                 }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {                 // the number of line segments
        return colinearSegmentsCount;
    }
    public LineSegment[] segments() {               // the line segments
        return colinearSegments.clone();
    }

    public static void main(String[] args) {
        /* YOUR CODE HERE */
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // Point[] points = new Point[4];
        // points[0] = new Point(0,1);
        // points[1] = new Point(1,2);
        // points[2] = new Point(2,3);
        // points[3] = new Point(3,4);

        // points[5] = new Point(21,1);
        // points[6] = new Point(20,1);

        // points[7] = new Point(0,100);
        // points[8] = new Point(0,8);
        // points[10] = new Point(0,9);

        // points[9] = new Point(6,6);

        // points[4] = new Point(99,77);

        // draw the points
        // StdDraw.enableDoubleBuffering();
        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);
        // StdDraw.setPenRadius(0.01);
        // for (Point p : points) {
        //     p.draw();
        // }
        // StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        // StdOut.println("done");

        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            // segment.draw();
        }
        // StdDraw.show();

     }
 }

