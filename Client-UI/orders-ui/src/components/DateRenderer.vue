<template>
    <div class="dateRenderer">
        <div v-if="!isEmpty(description)">{{description}}</div>
        <div>{{getCurrentDate()}}</div>
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
                currentTime: ""
            }
        },
        methods: {
            getCurrentDate() {
                this.$http.get("http://localhost:9000/api/get-time")
                    .then(response => this.currentTime = response.data.currentDate);
                return this.currentTime;
            },
            isEmpty(str) {
                return (!str || str.trim().length === 0)
            }
        }
    }
</script>