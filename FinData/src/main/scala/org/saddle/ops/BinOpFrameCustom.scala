package org.saddle.ops

import scala.{ specialized => spec }

import org.saddle._
import index._
import vec._
import java.util.Date

trait BinOpFrameCustom {
  final class FrFrDot[X: ST: ORD, Y: ST: ORD, @spec(Int, Long, Double) A, @spec(Int, Long, Double) B: ST, @spec(Int, Long, Double) C: ST: NUM](
    val opmul: BinOp[Multiply, Frame[X, Y, A], Frame[X, Y, B], Frame[X, Y, C]],
    val opadd: BinOp[Add, Series[X, C], Series[X, C], Series[X, C]])
      extends BinOp[InnerProd, Frame[X, Y, A], Frame[X, Y, B], Series[X, C]] {
    def apply(f1: Frame[X, Y, A], f2: Frame[X, Y, B]): Series[X, C] = {
      require(f1.colIx.length == f2.colIx.length, "Frames must have the same number of columns!")
      require(f1.rowIx.length == f2.rowIx.length, "Frames must have the same number of rows!")

      val (l, r) = f1.align(f2, OuterJoin, OuterJoin)
      val mul = opmul(l, r)
      val result = mul.toColSeq.foldLeft(Series.empty[X, C])((prev, element) => opadd(prev, element._2))
      result
    }
  }

  implicit def FrFrDotDDD[X, Y](implicit cm: ST[X], cmp: ORD[X], my: ST[Y], cmpY: ORD[Y], opmul: BinOp[Multiply, Frame[X, Y, Double], Frame[X, Y, Double], Frame[X, Y, Double]], opadd: BinOp[Add, Series[X, Double], Series[X, Double], Series[X, Double]]) = new FrFrDot[X, Y, Double, Double, Double](opmul, opadd)
  implicit def FrFrDotDateStringDDD(implicit cm: ST[Date], cmp: ORD[Date], my: ST[String], cmpString: ORD[String], opmul: BinOp[Multiply, Frame[Date, String, Double], Frame[Date, String, Double], Frame[Date, String, Double]], opadd: BinOp[Add, Series[Date, Double], Series[Date, Double], Series[Date, Double]]) = new FrFrDot[Date, String, Double, Double, Double](opmul, opadd)
}