# Necronomicon API
An API to make less repetitive code, sync changes and .


Integrating Necronomicon API in your project using CurseMaven (This does not yet support javadocs but I will probably implement a custom maven in the future)

```
repositories { 
  maven { 
    url "https://cursemaven.com" 
    content { includeGroup "curse.maven" } 
  } 
}
```

## Fabric
```
dependencies { 
  implementation fg.deobf("curse.maven:necronomicon-586157:${necronomicon_fileid}") 
}
```

## Forge

```
dependencies { 
  modImplementation "curse.maven:necronomicon-586157:${necronomicon_fileid}"
}
```


# Feature Comparision (Forge vs Fabric)

| Feature           | Fabric | Forge  |
|-------------------|--------|--------|
| v1/datagen        | ✅ Yes| ⚠️ Partial|
| v1/resource       | ❌ No | ✅ Yes|
| v1/worldgen       | ✅ Yes| ❌ No |
| v1/biomes         | WIP   |  WIP   |
| utils             | ✅ Yes| ✅ Yes|
