<template>
    <div>
        <div class="dateInfo" v-if="!isEmpty(description)" v-html="buildHeader()"></div>
    </div>
</template>
<script>
    export default {
        name: 'DateRenderer',
        props: {
            description: {
                type: String,
                default: () => ""
            }
        },
        data: () => {
            return {
                currentTime: "",
                currentSoapTime: ''
            }
        },
        created() {
            this.getCurrentDate();
            this.getSoapSystemDate()
        },
        methods: {
            getCurrentDate() {
                this.$http.get("/api/get-time")
                    .then(response => this.currentTime = response.data.currentDate);
                return this.currentTime;
            },
            isEmpty(str) {
                return (!str || str.trim().length === 0)
            },

            buildHeader() {
                return this.description +
                    ' время из <b>SOAP</b> сервиса: <b>' + this.currentSoapTime + '</b>' +
                    ' время из <b>REST</b> сервиса: <b>' + this.currentTime + '</b>';
            },

            getSoapSystemDate() {
                let xml =
                    '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gs="https://pavlovaleksei.github.io">\n' +
                    '    <soapenv:Header/>\n' +
                    '    <soapenv:Body>\n' +
                    '        <gs:getServerTimeRequest/>\n' +
                    '    </soapenv:Body>\n' +
                    '</soapenv:Envelope>'

                fetch('/soap/ws', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'text/xml'
                    },
                    body: xml
                }).then(response => {
                        if (response.status !== 200) this.$message({
                            type: 'error',
                            message: 'Ошибка при запросе данныхо'
                        });
                        return response.text();
                    }).then(result => this.currentSoapTime = result.match(/<ns2:time>([^"]+)/)[1].substr(0, 19))
                    .catch(console.error);
                return this.currentSoapTime;
            }
        }
    }
</script>