package com.xj.ml.generation

import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.LinearDataGenerator
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * author : kongcong  
  * number : 27
  * date : 2017/6/22
  */
object GeneratetorLinearRDDDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("GeneratetorLinearRDDDemo")
    val sc = new SparkContext(conf)

    /**
      * 此操作用于生成线性回归的训练数据样本数据，格式为RDD[LabelPoint],其参数如下
      *
      * RDD中包括的数据量 40
      * 样本的特征数 3
      * Epsilon因子 1.0  (好像是对标签列的数值进行了一定比例的缩放)
      * RDD分区数 2
      * 0.0 ？
      *
      */
    val linearRDD: RDD[LabeledPoint] = LinearDataGenerator.generateLinearRDD(sc, 40, 3, 4.0, 2, 0.0)

    println(linearRDD.count()) // 40

    val take = linearRDD.take(5)
    take.foreach(println(_))
    /**
      * Epsilon因子 1.0
      * (0.5148180443762232,[0.4551273600657362,0.36644694351969087,-0.38256108933468047])
      * (0.3661419809069756,[-0.4458430198517267,0.33109790358914726,0.8067445293443565])
      * (-0.6578217711510977,[-0.2624341731773887,-0.44850386111659524,-0.07269284838169332])
      * (0.8457359976097787,[0.5658035575800715,0.8386555657374337,-0.1270180511534269])
      * (2.4079345734665245,[0.499812362510895,-0.22686625128130267,-0.6452430441812433])
      *
      * Epsilon因子 2.0
      * (0.785747457068313,[0.4551273600657362,0.36644694351969087,-0.38256108933468047])
      * (0.9273912657207526,[-0.4458430198517267,0.33109790358914726,0.8067445293443565])
      * (-1.1876513491879583,[-0.2624341731773887,-0.44850386111659524,-0.07269284838169332])
      * (1.3847581891086063,[0.5658035575800715,0.8386555657374337,-0.1270180511534269])
      * (4.620274787645249,[0.499812362510895,-0.22686625128130267,-0.6452430441812433])
      *
      * Epsilon因子 4.0
      * (1.3276062824524923,[0.4551273600657362,0.36644694351969087,-0.38256108933468047])
      * (2.049889835348307,[-0.4458430198517267,0.33109790358914726,0.8067445293443565])
      * (-2.2473105052616797,[-0.2624341731773887,-0.44850386111659524,-0.07269284838169332])
      * (2.462802572106261,[0.5658035575800715,0.8386555657374337,-0.1270180511534269])
      * (9.044955216002698,[0.499812362510895,-0.22686625128130267,-0.6452430441812433])
      *
      */
  }
}
