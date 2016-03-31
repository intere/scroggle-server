package com.icolasoft.scroggle.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.icolasoft.scroggle.models.AbstractMongoDocument;


@Component
public class DocumentPersister<T extends AbstractMongoDocument> {

	private static final Logger LOG = Logger.getLogger(DocumentPersister.class);

	@Autowired
	private MongoTemplate operations;

	/**
	 * Generic Save Object Method.  Takes anything that extends {@link AbstractMongoDocument}.
	 * @param object
	 * @return
	 */
	public T save(T object) {
		object.updateModifiedDate();

		if(null == object.getFlatId()) {
			operations.insert(object);
			LOG.debug("Inserted new " + object.getClass().getSimpleName());
		} else {
			operations.save(object);
			LOG.debug("Updated existing " + object.getClass().getSimpleName());
		}

		return object;
	}

	/**
	 * Removes the provided object.
	 * @param object The Document to remove.
	 */
	public void nuke(T object) {
		operations.remove(object);
	}

}
