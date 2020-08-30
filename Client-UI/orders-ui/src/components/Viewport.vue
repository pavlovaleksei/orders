<template>
    <div class="viewport">
      <DateRenderer :description='dateRendererDescription' class="header"/>
      <OrderTable class="table"/>
    </div>
</template>
<script>
    import DateRenderer from './DateRenderer.vue'
    import OrderTable from './OrderTable.vue'

    export default {
        name: 'Viewport',
        components: {
            DateRenderer,
            OrderTable
        },
        data: () => {
            return {
                dateRendererDescription: "Информация о текущем времени:"
            }
        }
    }
</script>