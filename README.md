[![Maven Central](https://img.shields.io/maven-central/v/com.gianluz/slackalo.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.gianluz%22%20AND%20a:%22slackalo%22)
<p align="center">
<img src="https://raw.githubusercontent.com/gianluz/slackalo/master/design/cover.png" /></br></br>
A Kotlin library to build and send Slack messages via Slack webhook
</p>

## Dependency
Slackalo is available in maven central
```groovy
dependencies {
    implementation 'com.gianluz:slackalo:1.0'
}
```

## Introduction

Build your custom slack message just using the builder utils provided. 
Checkout the official Slack Block Kit Builder [here](https://api.slack.com/tools/block-kit-builder?mode=message)
Slackalo doesn't provide all the blocks right now, but you can contribute to make it more complete!
Here you can find a list of what currently Slackalo support:
- `Section` as `PlainText` or `MarkdownText` only
- `Image` as `ImageBlock`
- Section with `Fields` as `FieldsBlock`
- Section with `Image` as `ImagePlainTextBlock` or `ImageMarkdownBlock`
- `Divider` as `Divider`

## Usage

To create a slack webhook url please follow the official slack app guide [here](https://api.slack.com/messaging/webhooks)

Here an example:
```kotlin
val message = slackMessage {
    blocks {
        plainText("Hello this is a plaintext")
        markdown("Hello this is a markdown")
        divider()
        imageMarkdown(
            ":warning: This is a message with an image and slack emoji",
             "https://api.slack.com/img/blocks/bkb_template_images/palmtree.png",
             "alt text"
        )
        divider()
        fields { 
            markdown("Field 1")
            markdown("Field 2")
            markdown("Field 3")
        }
    }
}
    
with(DefaultWebHookClient()) {
    sendWebHook("MySlackWebHookUrl", message)
}

```

## Contribute

Please fork this repo, and add all the new functionalities you would like to have and open a Pull request!
