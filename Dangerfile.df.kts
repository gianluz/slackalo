@file:DependsOn("com.gianluz:slackalo:1.1")

import com.gianluz.slackalo.DefaultWebHookClient
import com.gianluz.slackalo.blocks
import com.gianluz.slackalo.plainText
import com.gianluz.slackalo.slackMessage
import systems.danger.kotlin.danger

danger(args) {
    val message = slackMessage {
        blocks {
            plainText("Using slackalo to send custom messages")
        }
    }
    val client = DefaultWebHookClient()
    client.sendWebHook(System.getenv("SLACK_WEBHOOK_URL"), message)
}