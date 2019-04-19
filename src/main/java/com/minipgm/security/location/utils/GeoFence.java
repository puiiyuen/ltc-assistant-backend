/**
 * Geo Fence
 *
 * @author Peiyuan
 * 2019-04-15
 */

package com.minipgm.security.location.utils;

import java.util.List;

public class GeoFence {


    /**
     * 返回一个点是否在一个多边形区域内
     *
     * @param mPoints 多边形坐标点列表
     * @param point   待判断点
     * @return true 多边形包含这个点,false 多边形未包含这个点。
     */
    public static boolean isPolygonContainsPoint(List<Point> mPoints, Point point) {
        int nCross = 0;
        for (int i = 0; i < mPoints.size(); i++) {
            Point p1 = mPoints.get(i);
            Point p2 = mPoints.get((i + 1) % mPoints.size());
            // 取多边形任意一个边,做点point的水平延长线,求解与当前边的交点个数
            // p1p2是水平线段,要么没有交点,要么有无限个交点
            if (p1.getY() == p2.getY())
                continue;
            // point 在p1p2 底部 --> 无交点
            if (point.getY() < Math.min(p1.getY(), p2.getY()))
                continue;
            // point 在p1p2 顶部 --> 无交点
            if (point.getY() >= Math.max(p1.getY(), p2.getY()))
                continue;
            // 求解 point点水平线与当前p1p2边的交点的 X 坐标
            double x = (point.getY() - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY()) + p1.getX();
            if (x > point.getX()) // 当x=point.x时,说明point在p1p2线段上
                nCross++; // 只统计单边交点
        }
        // 单边交点为偶数，点在多边形之外 ---
        return (nCross % 2 == 1);
    }

    /**
     * 返回一个点是否在一个多边形边界上
     *
     * @param mPoints 多边形坐标点列表
     * @param point   待判断点
     * @return true 点在多边形边上,false 点不在多边形边上。
     */
    public static boolean isPointInPolygonBoundary(List<Point> mPoints, Point point) {
        for (int i = 0; i < mPoints.size(); i++) {
            Point p1 = mPoints.get(i);
            Point p2 = mPoints.get((i + 1) % mPoints.size());
            // 取多边形任意一个边,做点point的水平延长线,求解与当前边的交点个数

            // point 在p1p2 底部 --> 无交点
            if (point.getY() < Math.min(p1.getY(), p2.getY()))
                continue;
            // point 在p1p2 顶部 --> 无交点
            if (point.getY() > Math.max(p1.getY(), p2.getY()))
                continue;

            // p1p2是水平线段,要么没有交点,要么有无限个交点
            if (p1.getY() == p2.getY()) {
                double minX = Math.min(p1.getX(), p2.getX());
                double maxX = Math.max(p1.getX(), p2.getX());
                // point在水平线段p1p2上,直接return true
                if ((point.getY() == p1.getY()) && (point.getX() >= minX && point.getX() <= maxX)) {
                    return true;
                }
            } else { // 求解交点
                double x = (point.getY() - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY()) + p1.getX();
                if (x == point.getX()) // 当x=point.x时,说明point在p1p2线段上
                    return true;
            }
        }
        return false;
    }
}
