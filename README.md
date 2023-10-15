# Necronomicon API
An API to make less repetitive code, sync changes and bring parity to both loaders with common code.

## Features
🛠️ Easy to use config API for both loaders.

🔄 Datagen utility API for Fabric (and partially for Forge)

🌍 Dynamic Resource Generation API for Forge

🌏 Worldgen utility API for Fabric (Mostly feature generation)

🌿 Biomes API (Work in progress)

🤝 Unifying utility methods that work for both loaders

## Using Necronomicon in your project

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
| v1/config         | ✅ Yes| ✅ Yes|
| v1/datagen        | ✅ Yes| ⚠️ Partial|
| v1/resource       | ✅ Yes| ✅ Yes|
| v1/worldgen       | ✅ Yes| ❌ No |
| v1/biomes         | WIP   |  WIP   |
| utils             | ✅ Yes| ✅ Yes|
