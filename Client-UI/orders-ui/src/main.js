import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import ru from "element-ui/lib/locale/lang/ru-RU";
import { Message } from 'element-ui';
import { MessageBox } from 'element-ui';

Vue.config.productionTip = false
Vue.use(VueAxios, axios)
Vue.use(ElementUi, { locale: ru });

new Vue({
  $message: Message,
  $confirm: MessageBox.confirm,
  $alert: MessageBox.alert,
  $prompt: MessageBox.prompt,
  $msgbox: MessageBox,
  render: h => h(App),
}).$mount('#app')