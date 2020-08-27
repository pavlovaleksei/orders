<template>
    <ul class="or-list">
        <li v-for="item in source" v-bind:key="item.id" class="or-list__parent">

            <div class="accordion" id="accordion" v-on:click="accordion">
                <div class="accordion-item" >
                    <h3 class="accordion-item-head">
                        {{item.customerName}}, {{item.customerAddres}}
                    </h3>
                    <div class="accordion-item-body">
                        {{item.details.productName}},  {{item.details.productNumber}}, {{item.details.productCount}}
                    </div>
                </div>

            </div>


        </li>
    </ul>
</template>


<script>
    export default {
        name: 'List',

        props: {
            source: {
                type: Array,
                default: () => []
            }
        },
        methods: {
            accordion(event) {

                //Раскомментировать, если нужно, чтобы закрывались остальные пункты при открытии текущего
                // 	var matches = document.querySelectorAll('.accordion-item-head');
                //  	for(var i = 0; i < matches.length; i++) {
                //  		matches[i].classList.remove('active');
                // 	 }

                console.log(event)

                event.target.classList.toggle('active');
            }
        }
    }
</script>