# scroggle-server


I created these projects with the following commands:

```bash
mvn archetype:generate -DgroupId=com.icolasoft.scroggle -DartifactId=models -DinteractiveMode=false \
  -DarchetypeGroupId=org.codehaus.groovy.maven.archetypes -DarchetypeArtifactId=gmaven-archetype-basic
mvn archetype:generate -DgroupId=com.icolasoft.scroggle -DartifactId=dao -DinteractiveMode=false \
    -DarchetypeGroupId=org.codehaus.groovy.maven.archetypes -DarchetypeArtifactId=gmaven-archetype-basic
mvn archetype:generate -DgroupId=com.icolasoft.scroggle -DartifactId=business -DinteractiveMode=false \
      -DarchetypeGroupId=org.codehaus.groovy.maven.archetypes -DarchetypeArtifactId=gmaven-archetype-basic
mvn archetype:generate -DgroupId=com.icolasoft.scroggle -DartifactId=services -DinteractiveMode=false \
        -DarchetypeGroupId=org.codehaus.groovy.maven.archetypes -DarchetypeArtifactId=gmaven-archetype-basic
```
