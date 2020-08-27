<template>
    <div class="viewport">
        <DateRenderer :description='dateRendererDescription'/>


        <List class="or-list"
              :source="listData"
        >

        </List>

    </div>
</template>
<script>
    import DateRenderer from './DateRenderer.vue'
    import List from '../utils/List.vue'

    export default {
        name: 'Viewport',
        components: {
            DateRenderer,
            List
        },
        data: () => {
            return {
                dateRendererDescription: " a",
                listData: []
            }
        },
        beforeCreate() {
            this.$http.get("http://localhost:9000/api/orders")
                .then(response => this.listData = response.data);
        },
        methods: {
        }

    }
</script>