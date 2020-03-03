<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="备件" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-multi-select-tag v-decorator="['attId', validatorRules.attId]" v-model="selectValue" :options="attAttList" placeholder="选择备件"/>
        </a-form-item>
        <a-form-item label="领取人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-multi-select-tag v-decorator="['sysUserId', validatorRules.sysUserId]" v-model="selectValue" :options="sysUserList" placeholder="选择领取人" />
        </a-form-item>
        <a-form-item label="数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'num', validatorRules.num]" placeholder="请输入数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="出库时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择出库时间" v-decorator="[ 'outDateTime', validatorRules.outDateTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'remark', validatorRules.remark]" placeholder="请输入备注"/>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction ,getAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'
  import JMultiSelectTag from '@/components/dict/JMultiSelectTag'

  export default {
    name: "AttOutStorehouseModal",
    components: { 
      JDate,
      JMultiSelectTag
    },
    data () {
      return {
        selectValue:"",
        attAttList:[],
        sysUserList:[],
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          attId: {rules: [
          ]},
          sysUserId: {rules: [
          ]},
          num: {rules: [
            {required: true, message: '请输入数量!'},
          ]},
          outDateTime: {rules: [
            {required: true, message: '请输入出库时间!'},
          ]},
          remark: {rules: [
          ]},
        },
        url: {
          add: "/business/attOutStorehouse/add",
          edit: "/business/attOutStorehouse/edit",
        }
      }
    },
    created () {
    },
    methods: {
      initData(){
        //备件下拉列表
        getAction(`/business/attAttachment/list`).then(res=>{
          this.loading=false;
          if(res.success){
            let list = res.result.records;
            let array = [];
            list.forEach( item =>{
              array.push({label:item.attName,value:item.id})
            });
            this.attAttList = array;
          }else{
            this.$message.warning(res.message)
          }
        });
        //人员下拉列表
        getAction(`/sys/user/list`).then(res=>{
          this.loading=false;
          if(res.success){
            let list = res.result.records;
            let array = [];
            list.forEach( item =>{
              array.push({label:item.realname,value:item.id})
            });
            this.sysUserList = array;
          }else{
            this.$message.warning(res.message)
          }
        });
      },
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'attId','sysUserId','num','outDateTime','remark'));
          this.initData();
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }
         
        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'attId','sysUserId','num','outDateTime','remark'))
      },

      
    }
  }
</script>