
package com.icolasoft.scroggle.models

class Word extends AbstractMongoDocument {
  String word

  Word() {
    super()
  }

  Word(String word) {
    super()
    this.word = word
  }
}
