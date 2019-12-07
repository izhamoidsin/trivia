package com.adaptionsoft.games.trivia.runner

import java.io.{File, FileOutputStream}
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, Matchers}

class GameRunnerSpec extends FlatSpec with Matchers with BeforeAndAfterAll {
  behavior of "Game"

  val randomSeed = 42L.toString
  val wrongAnswerBound = 100.toString
  val gmFileName = s"goldenMaster.log"
  val testFileName = s"test.log"

  it should "catch GM output" in {
    var fos: FileOutputStream = null
    try {
      fos = new FileOutputStream(new File(gmFileName))
      Console.withOut(fos) {
        GameRunnerSameRandomConfigurable.main(Array(randomSeed, wrongAnswerBound))
      }

    } finally {
      if (fos != null) fos.close()
      println(s"GM output size is: ${io.Source.fromFile(gmFileName).getLines().size}")
    }
  }

  it should "compare output with GM" in {
    var fos: FileOutputStream = null

    try {
      fos = new FileOutputStream(new File(testFileName))
      Console.withOut(fos) {
        GameRunnerSameRandomConfigurable.main(Array(randomSeed, wrongAnswerBound))
      }

    } finally {
      if (fos != null) fos.close()

      val testOutput = io.Source.fromFile(testFileName).getLines().toList
      val gmOutput = io.Source.fromFile(gmFileName).getLines().toList

      testOutput === gmOutput
    }
  }

  override protected def beforeAll(): Unit = {
    new File(gmFileName).delete()
    new File(testFileName).delete()
  }
}
