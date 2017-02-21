package cn.vlooks.www.app.tools;

import java.util.List;

import cn.vlooks.www.app.bean.Point;

/**
 * Created by Administrator on 2016/8/16.
 */
public class Inpolygon {

    /**
     * 功能：判断点是否在多边形内 方法：求解通过该点的水平线与多边形各边的交点 结论：单边交点为奇数，成立!
     *
     * @param point   指定的某个点
     * @param APoints 多边形的各个顶点坐标（首末点可以不一致）
     * @return
     */
    public static boolean PtInPolygon(Point point, List<Point> APoints) {
        int nCross = 0;
        for (int i = 0; i < APoints.size(); i++) {
            Point p1 = APoints.get(i);
            Point p2 = APoints.get((i + 1) % APoints.size());
            // 求解 y=p.y 与 p1p2 的交点
            if (p1.getY() == p2.getY()) // p1p2 与 y=p0.y平行
                continue;
            if (point.getY() < Math.min(p1.getY(), p2.getY())) // 交点在p1p2延长线上
                continue;
            if (point.getY() >= Math.max(p1.getY(), p2.getY())) // 交点在p1p2延长线上
                continue;
            // 求交点的 X 坐标
            // --------------------------------------------------------------
            double x = (double) (point.getY() - p1.getY())
                    * (double) (p2.getX() - p1.getX())
                    / (double) (p2.getY() - p1.getY()) + p1.getX();
            if (x > point.getX())
                nCross++; // 只统计单边交点
        }
        // 单边交点为偶数，点在多边形之外 ---
        return (nCross % 2 == 1);
    }
}
