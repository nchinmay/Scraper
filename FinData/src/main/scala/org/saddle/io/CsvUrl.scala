package org.saddle.io

import org.saddle.UTF8
import java.io.{ BufferedReader, InputStreamReader }
import java.net.URL

class CsvUrl(url: String, encoding: String = UTF8) extends CsvSource {
  private val urlHandle = new URL(url);
  private val reader = new BufferedReader(new InputStreamReader(urlHandle.openStream()));

  def readLine = {
    val line = reader.readLine()
    if (line == null) reader.close()
    line
  }

  override def toString = "CsvUrl(%s, encoding: %s)".format(url, encoding)
}

object CsvUrl {
  def apply(url: String, encoding: String = UTF8) = new CsvUrl(url, encoding)
}