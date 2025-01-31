package com.intenthq.challenge

import scala.util.matching.Regex

object SNiceStrings {

// From http://adventofcode.com/day/5
//  --- Day 5: Doesn't He Have Intern-Elves For This? ---
//
//  Santa needs help figuring out which strings in his text file are naughty or nice.
//
//    A nice string is one with all of the following properties:
//
//    It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
//  It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
//    It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
//    For example:
//
//    ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
//  aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
//    jchzalrnumimnmhp is naughty because it has no double letter.
//    haegwjzuvuyypxyu is naughty because it contains the string xy.
//    dvszwmarrgswjxmb is naughty because it contains only one vowel.
//    How many strings are nice?

  def nice(santasTextFile: List[String]): Int = {

    val listOfNaughtyStrings: List[String] = List("ab", "cd", "pq", "xy")
    val regex : Regex = "[aeiou]".r

    santasTextFile.map {
      string =>
        val containsThreeVowels = if (regex.findAllMatchIn(string.toLowerCase).size > 2) true else false
        val containsDoubleChars = if (string.sliding(2).count(char => char(0) == char(1)) > 0) true else false
        val doesNotContainNaughtyStrings = !listOfNaughtyStrings.exists(naughtyString => string.toLowerCase.contains(naughtyString))

        if (containsThreeVowels && containsDoubleChars && doesNotContainNaughtyStrings) {
          1
        } else {
          0
        }
    }.head
  }

}
