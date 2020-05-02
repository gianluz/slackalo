package com.gianluz.slackalo

import khttp.responses.Response

interface WebHookClient<T> {
    /**
     * Send a POST to the webhook url with the SlackMessage as JSON
     * @param slackWebHookUrl the webHookUrl
     * @param slackMessage the SlackMessage
     * @return the post response
     */
    fun sendWebHook(slackWebHookUrl: String, slackMessage: SlackMessage): T
}

class DefaultWebHookClient : WebHookClient<Response> {
    override fun sendWebHook(slackWebHookUrl: String, slackMessage: SlackMessage): Response {
        return khttp.post(
                slackWebHookUrl,
                headers = mapOf("Content-type" to "application/json"),
                json = slackMessage.asSlackBlockKitMap()
        )
    }
}