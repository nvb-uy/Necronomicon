# Necronomicon API
An API to make less repetitive code, sync changes across all my mods and easing the making of various stuff.


Integrating Necronomicon API in your project using CurseMaven:

```
repositories { 
  maven { 
    url "https://cursemaven.com" 
    content { includeGroup "curse.maven" } 
  } 
}
```

```
dependencies { 
  implementation fg.deobf("curse.maven:necronomicon-586157:{fileid}") 
}
```
