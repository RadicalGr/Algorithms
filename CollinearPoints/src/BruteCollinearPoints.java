// https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php
// https://algs4.cs.princeton.edu/22mergesort/



public class BruteCollinearPoints {
    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {   // finds all line segments containing 4 points
        if ( points == null ) throw new IllegalArgumentException();
        
        for (int p = 0; p < points.length; p++) {
            for (int q = 0; q < points.length; q++) {
                for (int r = 0; r < points.length; r++) {
                    for (int s = 0; s < points.length; s++) {
                        if (points[p].slopeTo(points[q]) == )
                    }
                }
            }
        }
    
    }

    public int numberOfSegments() {                 // the number of line segments

    }
    public LineSegment[] segments() {               // the line segments
        private add(LineSegment){
            segments[i] 
        }


    }
 }

