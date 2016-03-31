
package com.icolasoft.scroggle.models

import groovy.util.GroovyTestCase

/**
 * Tests for the {@link Example} class.
 */
class WordTest
    extends GroovyTestCase
{
    void testCreation() {
      Word obj = new Word()
      obj.word = "test"
      assertNotNull(obj)
    }
}
