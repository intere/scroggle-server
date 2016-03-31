package com.icolasoft.scroggle.dao

import groovy.util.GroovyTestCase
import java.util.ArrayList
import java.util.Date
import java.util.List
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.icolasoft.scroggle.models.Word
import com.icolasoft.scroggle.config.TestConfig

import static org.junit.Assert.*

/**
 * Tests for the {@link Example} class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
class DocumentQueryTest
    extends GroovyTestCase
{
  @Autowired DocumentQuery documentQuery
  @Autowired DocumentPersister<Word> wordPersister

  @Before
  void setUp() {
    def words = ["this", "that", "the", "other"]

    for (word in words) {
      wordPersister.save(new Word(word))
    }
  }

  @After
  void tearDown() {
    for (word in documentQuery.words()) {
      wordPersister.nuke(word)
    }
  }

  @Test
  void testInstance() {
    assertNotNull(documentQuery)
  }

  @Test
  void testWords() {
    assertEquals(4, documentQuery.words().size())
  }
}
