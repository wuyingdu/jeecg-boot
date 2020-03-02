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

        <a-form-item label="备件id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'attId', validatorRules.attId]" placeholder="请输入备件id"></a-input>
        </a-form-item>
        <a-form-item label="供应商id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'supId', validatorRules.supId]" placeholder="请输入供应商id"></a-input>
        </a-form-item>
        <a-form-item label="数量" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'num', validatorRules.num]" placeholder="请输入数量" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="入库时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择入库时间" v-decorator="[ 'inDateTime', validatorRules.inDateTime]" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'remark', validatorRules.remark]" placeholder="请输入备注"></a-input>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  

  export default {
    name: "AttInStorehouseModal",
    components: { 
      JDate,
    },
    data () {
      return {
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
          supId: {rules: [
          ]},
          num: {rules: [
            {required: true, message: '请输入数量!'},
          ]},
          inDateTime: {rules: [
            {required: true, message: '请输入入库时间!'},
          ]},
          remark: {rules: [
          ]},
        },
        url: {
          add: "/business/attInStorehouse/add",
          edit: "/business/attInStorehouse/edit",
        }
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'attId','supId','num','inDateTime','remark'))
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
        this.form.setFieldsValue(pick(row,'attId','supId','num','inDateTime','remark'))
      },

      
    }
  }
</script>