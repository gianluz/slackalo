package com.gianluz.slackalo

interface WebHookClient<T> {
    /**
     * Send a POST to the webhook url with the SlackMessage as JSON
     * @param slackWebHookUrl the webHookUrl
     * @param slackMessage the SlackMessage
     * @return the post response
     */
    fun sendWebHook(slackWebHookUrl: String, slackMessage: SlackMessage): T
}

data class StatusCode(val value: Int)

class DefaultWebHookClient : WebHookClient<StatusCode> {
    override fun sendWebHook(slackWebHookUrl: String, slackMessage: SlackMessage): StatusCode {
        return khttp.post(
            slackWebHookUrl,
            headers = mapOf("Content-type" to "application/json"),
            json = slackMessage.asSlackBlockKitMap()
        ).let { response ->
            StatusCode(response.statusCode)
        }
    }
}