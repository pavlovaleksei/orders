<template>
  <div>
    <el-button size="mini" type="primary" @click="isActive.addOrder = true">Добавить заказ</el-button>
    <el-collapse accordion>
      <el-collapse-item v-for="order in model.detailOrders" v-bind:key="order.id" :name="order.id">
        <template slot="title">
          <i class="el-icon-sold-out" style="font-size: 1rem; color: dodgerblue"></i>
          <div class="titleOrderText" v-html="getTitleOrderText(order)"/>
          <div class="detailLength">
            Кол-во товаров: <b>{{order.details.length}}</b>
          </div>
          <div class="order_btn">
            <el-button v-on:click="showEditOrder(order)" type="text" size="small"><i class="el-icon-edit-outline btnEdit"></i></el-button>
            <el-button v-on:click="deleteOrder(order)" type="text" size="small"><i class="el-icon-delete-solid btnDelete"></i></el-button>
          </div>
        </template>
        <div class="detailTable">
          <div>
            <template>
              <el-button size="mini" type="primary" @click="showAddDetail(order)">Добавить товар</el-button>
              <el-table
                  :data="order.details"
                  style="width: 100%">
                <el-table-column
                    prop="productserialnum"
                    label="Серийный номер продукта"/>
                <el-table-column
                    prop="productname"
                    label="Наименование продукта"/>
                <el-table-column
                    prop="count"
                    label="Количество"/>
                <el-table-column
                    fixed="right"
                    label="Действия"
                    width="120">
                  <template slot-scope="scope">
                    <el-button @click="showEditDetail(scope.row)" type="text" size="small"><i class="el-icon-edit-outline btnEdit"></i></el-button>
                    <el-button @click="deleteDetail(scope.row)" type="text" size="small"><i class="el-icon-delete-solid btnDelete"></i></el-button>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </div>
        </div>
      </el-collapse-item>
    </el-collapse>

    <el-dialog
        title="Добавление заказа"
        :visible.sync="isActive.addOrder"
        :close-on-click-modal="false"
        :width="'20%'"
        center>
            <span>
                <el-input placeholder="Наименование заказа" v-model="element.order.customerName"></el-input>
                <el-input placeholder="Адрес" v-model="element.order.customerAddr"></el-input>
                <el-input placeholder="Сумма" v-model="element.order.orderSum"></el-input>
            </span>
      <span slot="footer" class="dialog-footer">
            <el-button @click="isActive.addOrder = false">Отмена</el-button>
            <el-button type="primary" @click="addOrder">Сохранить</el-button>
            </span>
    </el-dialog>

    <el-dialog
        title="Редактирование заказа"
        :visible.sync="isActive.updateOrder"
        :close-on-click-modal="false"
        :width="'20%'"
        center>
            <span>
                <el-input placeholder="Наименование заказа" v-model="element.order.customerName"></el-input>
                <el-input placeholder="Адрес" v-model="element.order.customerAddr"></el-input>
                <el-input placeholder="Сумма" v-model="element.order.orderSum"></el-input>
            </span>
      <span slot="footer" class="dialog-footer">
            <el-button @click="isActive.updateOrder = false">Отмена</el-button>
            <el-button type="primary" @click="editOrder">Сохранить</el-button>
            </span>
    </el-dialog>

    <el-dialog
        title="Добавление товара"
        :visible.sync="isActive.addDetail"
        :close-on-click-modal="false"
        :width="'20%'"
        center>
            <span>
                <el-input placeholder="Серийный номер продукта" v-model="element.detail.productSerialNum"></el-input>
                <el-input placeholder="Наименование" v-model="element.detail.productName"></el-input>
                <el-input placeholder="Количество" v-model="element.detail.count"></el-input>
            </span>
      <span slot="footer" class="dialog-footer">
            <el-button @click="isActive.addDetail = false">Отмена</el-button>
            <el-button type="primary" @click="addDetail">Сохранить</el-button>
            </span>
    </el-dialog>

    <el-dialog
        title="Редактирование товара"
        :visible.sync="isActive.updateDetail"
        :close-on-click-modal="false"
        :width="'20%'"
        center>
            <span>
                <el-input placeholder="Наименование заказа" v-model="element.detail.productSerialNum"></el-input>
                <el-input placeholder="Адрес" v-model="element.detail.productName"></el-input>
                <el-input placeholder="Сумма" v-model="element.detail.count"></el-input>
            </span>
      <span slot="footer" class="dialog-footer">
            <el-button @click="isActive.updateDetail = false">Отмена</el-button>
            <el-button type="primary" @click="editDetail">Сохранить</el-button>
            </span>
    </el-dialog>
  </div>
</template>
<script>
import _ from 'lodash';

export default {
  name: 'OrderTable',
  data() {
    return {
      source: [],
      model: {
        orders: [],
        detailOrders: []
      },
      isActive: {
        addOrder: false,
        updateOrder: false,
        addDetail: false,
        updateDetail: false
      },
      element: {
        order: {
          id: 0,
          customerName: '',
          customerAddr: '',
          createDate: '',
          orderSum: ''
        },
        detail: {
          detailId: 0,
          productSerialNum: '',
          productName: '',
          count: 0,
          orderId: 0
        }
      }
    }
  },
  created() {
    this.initOrders();
  },
  methods: {
    initOrders() {
      let me = this;
      this.$http.get("/api/orders")
          .then(response => {
            this.source = response.data
            this.$http.get("/api/all-details")
                .then(response => response.data)
                .then(details => {
                  me.model.orders = me.source;
                  me.model.orders.forEach(order => {
                    me.model.detailOrders.push(
                        {
                          id: order.id,
                          customerName: order.customername,
                          customerAddr: order.customeraddr,
                          orderSum: order.ordersum,
                          createDate: order.createdate,
                          details: _.filter(details, (v) => v.orderid === order.id)
                        }
                    )
                  })
                }).catch(console.error);
          }).catch(console.error);
    },

    getTitleOrderText(order) {
      let date = new Date(order.createDate).toISOString().slice(0, 19).replace("T", " ");
      return "<u>(" + date + ")</u>" +
          " <b>Заказ:</b> " + order.customerName +
          ", <b>адрес:</b> " + order.customerAddr +
          ", <b>сумма:</b> " + order.orderSum + " руб.";
    },

    addOrder() {
      let me = this;
      let obj = {
        customername: this.element.order.customerName,
        customeraddr: this.element.order.customerAddr,
        ordersum: parseInt(this.element.order.orderSum)
      }
      this.$http.put("/api/order", obj)
          .then(response => response.data).then(() => me.clearData())
    },

    showEditOrder(order) {
      this.isActive.updateOrder = true
      this.element.order = order
    },

    editOrder() {
      let me = this;
      let obj = {
        id: this.element.order.id,
        customername: this.element.order.customerName,
        customeraddr: this.element.order.customerAddr,
        ordersum: parseInt(this.element.order.orderSum)
      }
      this.$http.post("/api/order", obj)
          .then(response => response.data).then(() => me.clearData())
    },

    deleteOrder(order) {
      let me = this;
      this.$confirm('Вы действительно хотите удалить заказ?', 'Внимание', {
        confirmButtonText: 'Да',
        cancelButtonText: 'Отмена',
        type: 'warning'
      }).then(() => {
        this.$http.delete("/api/order?id=" + order.id)
            .then(response => response.data)
            .then(() => {
              me.clearData()
              me.$message({
                type: 'success',
                message: 'Заказ удален'
              });
            })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Удаление отменено'
        });
      });
    },

    showAddDetail(order) {
      this.isActive.addDetail = true;
      this.element.detail.orderId = order.id
    },

    addDetail() {
      let el = this.element.detail;
      let me = this;
      let obj = {
        productserialnum: el.productSerialNum,
        productname: el.productName,
        count: parseInt(el.count),
        orderid: el.orderId
      }
      this.$http.put("/api/detail", obj)
          .then(response => response.data)
          .then(() => me.clearData())
          .catch(console.error)
    },

    showEditDetail(detail) {
      this.isActive.updateDetail = true
      this.element.detail = {
        detailId: detail.detailid,
        productSerialNum: detail.productserialnum,
        productName: detail.productname,
        count: detail.count,
        orderId: detail.orderid
      }
    },

    editDetail() {
      let el = this.element.detail;
      let me = this;
      let obj = {
        detailid: el.detailId,
        productserialnum: el.productSerialNum,
        productname: el.productName,
        count: el.count,
        orderid: el.orderId
      }
      this.$http.post("/api/detail", obj)
          .then(response => response.data)
          .then(() => me.clearData())
    },

    deleteDetail(detail) {
      let me = this;
      this.$confirm('Вы действительно хотите удалить товар?', 'Внимание', {
        confirmButtonText: 'Да',
        cancelButtonText: 'Отмена',
        type: 'warning'
      }).then(() => {
        this.$http.delete("/api/detail?detailId=" + detail.detailid)
            .then(response => response.data)
            .then(() => {
              me.clearData()
              me.$message({
                type: 'success',
                message: 'Товар удален'
              });
            })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Удаление товара отменено'
        });
      });
    },

    clearData() {
      this.element = {
        order: {
          id: 0,
          customerName: '',
          customerAddr: '',
          createDate: '',
          orderSum: ''
        },
        detail: {
          detailId: 0,
          productSerialNum: '',
          productName: '',
          count: 0,
          orderId: 0
        }
      }
      this.model = {
        orders: [],
        detailOrders: []
      }
      this.isActive = {
        addOrder: false,
        updateOrder: false,
        addDetail: false,
        updateDetail: false
      }
      this.initOrders()
    }
  }
}
</script>