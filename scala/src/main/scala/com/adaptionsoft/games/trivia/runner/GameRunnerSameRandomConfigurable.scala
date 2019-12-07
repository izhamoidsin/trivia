package com.adaptionsoft.games.trivia.runner

import java.util.Random

import com.adaptionsoft.games.uglytrivia.Game

object GameRunnerSameRandomConfigurable {
  var notAWinner = false

  def main(args: Array[String]) {
    var aGame = new Game();
    aGame.add("Chet")
    aGame.add("Pat")
    aGame.add("Sue")

    var rand: Random = new Random(args(0).toLong)

    val wrongAnswerBound = args(1).toInt

    do {
      aGame.roll(rand.nextInt(5) + 1)
      if (rand.nextInt(wrongAnswerBound) == 7) {
        notAWinner = aGame.wrongAnswer
      }
      else {
        notAWinner = aGame.wasCorrectlyAnswered
      }
    } while (notAWinner)
  }
}