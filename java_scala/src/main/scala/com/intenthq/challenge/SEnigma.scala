package com.intenthq.challenge

import scala.annotation.tailrec
;

object SEnigma {

  // We have a system to transfer information from one place to another. This system
  // involves transferring only list of digits greater than 0 (1-9). In order to decipher
  // the message encoded in the list you need to have a dictionary that will allow
  // you to do it following a set of rules:
  //    > Sample incoming message: (​1,2,3,7,3,2,3,7,2,3,4,8,9,7,8)
  //    > Sample dictionary (​23->‘N’,234->‘ ’,89->‘H’,78->‘Q’,37 ->‘A’)
  //  - Iterating from left to right, we try to match sublists to entries of the map.
  //    A sublist is a sequence of one or more contiguous entries in the original list,
  //    eg. the sublist (1, 2) would match an entry with key 12, while the sublist (3, 2, 3)
  //    would match an entry with key 323.
  //  - Whenever a sublist matches an entry of the map, it’s replaced by the entry value.
  //    When that happens, the sublist is consumed, meaning that its elements can’t be used
  //    for another match. The elements of the mapping however, can be used as many times as needed.
  //  - If there are two possible sublist matches, starting at the same point, the longest one
  //    has priority, eg 234 would have priority over 23.
  //  - If a digit does not belong to any matching sublist, it’s output as is.
  //
  // Following the above rules, the message would be: “1N73N7 HQ”
  // Check the tests for some other (simpler) examples.

  final case class Match(value: Char, keyLength: Int)

  def deciphe(dictionary: Map[Int, Char])(incomingMessage: List[Int]): String = {

    @tailrec
    def getMatchOrShortenKey(key: Int): Match =
      dictionary.get(key) match {
        case Some(value) => Match(value, key.toString.length)
        case None if key.toString.length == 1 => Match(key.toString.head, 1)
        case _ => getMatchOrShortenKey(key.toString.dropRight(1).toInt)
      }

    @tailrec
    def iterateMessage(message: Seq[Int], result: String = ""): String = {
      message match {
        case Nil => result
        case _ =>
          val matchedKey: Match = getMatchOrShortenKey(message.take(3).mkString.toInt)
          iterateMessage(message.drop(matchedKey.keyLength), result :+ matchedKey.value)
      }
    }
    iterateMessage(incomingMessage)
  }
  
}
