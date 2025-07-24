![](images/banner.png)

# Snizzors

_Seamless UIKit View Overlay for Compose Multiplatform_

Snizzors is a Compose Multiplatform library that enables you to effortlessly display UIKit views *above* your Compose content. Traditionally, integrating UIKit views within Compose Multiplatform has meant the UIKit content renders beneath the Compose view, requiring a "hole" to be cut in the Compose layout to make the UIKit content visible. Snizzors solves this by allowing true overlay, making transparent `UIView`s and `UIViewController`s viable – a capability not natively supported with standard Compose Multiplatform integration.

## Why Snizzors?

Compose Multiplatform is powerful, but when it comes to interoperability with existing UIKit components, developers often encounter a significant limitation: the default rendering order places UIKit content *under* Compose content. This creates challenges, especially for designs requiring transparency or complex layering, as transparent UIKit views simply wouldn't show through.

Snizzors solves this by:

* **Enabling true overlay:** UIKit content can now sit on top of your Compose UI, just as you'd expect.
* **Supporting transparent UIKit views:** Integrate transparent `UIView`s and `UIViewController`s seamlessly into your Compose Multiplatform app.
* **Simplifying complex UIs:** Achieve sophisticated layering and visual effects that were previously difficult or impossible.

## Getting Started

To integrate Snizzors into your Compose Multiplatform project, follow these steps:

1.  **Add the dependency:**

    ```kotlin
    // In your module's build.gradle.kts
    iosMain {
      implementation("com.infiniteretry.snizzors:snizzors:1.0.0")
    }
    ```

2.  **Replace usages of the `UIKitView` composable with `SnizzorsUIView` and usages of `UIKitViewController` with `SnizzorsUIViewController`:**

    ```kotlin
    @Composable
    fun MyScreen() {
      SnizzorsUIView(
        factory = { MyTransparentUIView() },
        modifier = Modifier.size(300.dp),
      )
    
      SnizzorsUIViewController(
        factory = { MyTransparentUIViewController() },
        modifier = Modifier.size(300.dp),
      )
    }
    ```

## How It Works

Snizzors manages the view hierarchy on iOS, ensuring that the specified UIKit view or view controller is added to a layer *above* the Compose content, rather than below it. This enables the correct rendering of transparency and allows for a more natural integration of native UI components.

## License

```
Copyright 2025 Infinite Retry Labs

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```