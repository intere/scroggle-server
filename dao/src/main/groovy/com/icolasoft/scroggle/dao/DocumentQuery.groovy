
package com.icolasoft.scroggle.dao

import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.List

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.BasicQuery
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

import com.icolasoft.scroggle.models.Word

@Component
class DocumentQuery {
  final String ID = "_id"

  @Autowired MongoOperations operations

  List<Word> words() {
    operations.findAll(Word.class)
  }
}
