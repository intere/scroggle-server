
package com.icolasoft.scroggle.models

import java.io.Serializable

import java.util.Date
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

class AbstractMongoDocument implements Serializable {
  @Id ObjectId id
  String flatId
  Date creationDate
  Date modifiedDate

  AbstractMongoDocument() {
    creationDate = new Date()
    modifiedDate = new Date()
  }

  def updateModifiedDate() {
    modifiedDate = new Date()
  }

  String getFlatId() {
    if( null!=getId() ) {
			return getId().toString()
		}

		flatId
  }
}
