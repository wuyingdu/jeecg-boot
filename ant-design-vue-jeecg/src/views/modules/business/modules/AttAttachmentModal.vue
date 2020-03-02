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

        <a-form-item label="备件编码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'attCode', validatorRules.attCode]" placeholder="请输入备件编码"></a-input>
        </a-form-item>
        <a-form-item label="备件名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'attName', validatorRules.attName]" placeholder="请输入备件名称"></a-input>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'remark', validatorRules.remark]" placeholder="请输入备注"></a-input>
        </a-form-item>
        <a-form-item label="设备id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-search-select-tag v-decorator="['equId', validatorRules.equId]" dict="" />
        </a-form-item>
        <a-form-item label="供应商id" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-search-select-tag v-decorator="['supId', validatorRules.supId]" dict="" />
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JSearchSelectTag from '@/components/dict/JSearchSelectTag'

  export default {
    name: "AttAttachmentModal",
    components: { 
      JSearchSelectTag,
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
          attCode: {rules: [
            {required: true, message: '请输入备件编码!'},
          ]},
          attName: {rules: [
            {required: true, message: '请输入备件名称!'},
          ]},
          remark: {rules: [
          ]},
          equId: {rules: [
          ]},
          supId: {rules: [
          ]},
        },
        url: {
          add: "/business/attAttachment/add",
          edit: "/business/attAttachment/edit",
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
          this.form.setFieldsValue(pick(this.model,'attCode','attName','remark','equId','supId'))
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
        this.form.setFieldsValue(pick(row,'attCode','attName','remark','equId','supId'))
      },

      
    }
  }
</script>