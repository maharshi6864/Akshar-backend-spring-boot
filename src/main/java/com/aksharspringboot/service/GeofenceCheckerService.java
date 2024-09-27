package com.aksharspringboot.service;

import com.aksharspringboot.model.ClassRoomVo;
import com.aksharspringboot.model.PointVo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GeofenceCheckerService {


    // Define a method to convert ClassRoomVo coordinates to a polygon
    public static PointVo[] convertClassRoomVoToPolygon(ClassRoomVo classRoomVo) {
        return new PointVo[]{
                new PointVo(classRoomVo.getTopLeftLatitude(), classRoomVo.getTopLeftLongitude()),
                new PointVo(classRoomVo.getTopRightLatitude(), classRoomVo.getTopRightLongitude()),
                new PointVo(classRoomVo.getBottomRightLatitude(), classRoomVo.getBottomRightLongitude()),
                new PointVo(classRoomVo.getBottomLeftLatitude(), classRoomVo.getBottomLeftLongitude())
        };
    }

    // Method to check if a PointVo is inside a polygon
    public static boolean isPointVoInsidePolygon(PointVo[] polygon, PointVo PointVo) {
        int n = polygon.length;
        if (n < 3) return false; // A polygon must have at least 3 sides

        PointVo extreme = new PointVo(PointVo.latitude, Double.MAX_VALUE);
        int count = 0, i = 0;
        do {
            int next = (i + 1) % n;

            if (doIntersect(polygon[i], polygon[next], PointVo, extreme)) {
                if (orientation(polygon[i], PointVo, polygon[next]) == 0) {
                    return onSegment(polygon[i], PointVo, polygon[next]);
                }
                count++;
            }
            i = next;
        } while (i != 0);

        return (count % 2 == 1);
    }

    // Helper method to check if two line segments intersect
    private static boolean doIntersect(PointVo p1, PointVo q1, PointVo p2, PointVo q2) {
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4) {
            return true;
        }

        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false;
    }

    // Helper method to find the orientation of an ordered triplet (p, q, r).
    private static int orientation(PointVo p, PointVo q, PointVo r) {
        double val = (q.longitude - p.longitude) * (r.latitude - q.latitude) - (q.latitude - p.latitude) * (r.longitude - q.longitude);
        if (val == 0) return 0;  // Collinear
        return (val > 0) ? 1 : 2; // Clockwise or Counterclockwise
    }

    // Helper method to check if a PointVo q lies on line segment 'pr'
    private static boolean onSegment(PointVo p, PointVo q, PointVo r) {
        return q.latitude <= Math.max(p.latitude, r.latitude) && q.latitude >= Math.min(p.latitude, r.latitude) &&
                q.longitude <= Math.max(p.longitude, r.longitude) && q.longitude >= Math.min(p.longitude, r.longitude);
    }

    public boolean checkPresenseOfStudent(ClassRoomVo classRoomVo,PointVo pointVo) {

        PointVo[] classroomPolygon = convertClassRoomVoToPolygon(classRoomVo);

         return isPointVoInsidePolygon(classroomPolygon, pointVo);

    }
}
