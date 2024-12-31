<template>
    <div>

        <div style="margin: 10px 0">
            <span style="font-size: 13px">区域名称：</span>
            <el-select v-model="id" filterable placeholder="请选择" @change="handleSelectionChange">
                <el-option
                        v-for="item in areaoption"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                </el-option>
            </el-select>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0; margin-top: 20px; margin-bottom: 20px">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-popconfirm
                    class="ml-5"
                    confirm-button-text='确定'
                    cancel-button-text='我再想想'
                    icon="el-icon-info"
                    icon-color="red"
                    title="您确定批量删除这些数据吗？"
                    @confirm="delBatch"
            >
                <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleButtonClick">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="编号" ></el-table-column>
            <el-table-column prop="name" label="区域名称"></el-table-column>
            <el-table-column label="操作" width="200" align="center">
                <template slot-scope="scope">
                    <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-popconfirm
                            class="ml-5"
                            confirm-button-text='确定'
                            cancel-button-text='我再想想'
                            icon="el-icon-info"
                            icon-color="red"
                            title="您确定删除吗？"
                            @confirm="del(scope.row.id)"
                    >
                        <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                    </el-popconfirm>

                </template>
            </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[2, 5, 10, 20]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>

            <el-dialog title="区域信息" :visible.sync="dialogFormVisible" width="40%">
                <el-form label-width="80px" size="small">
                    <el-form-item label="区域名称">
                        <el-input v-model="form.name" autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="save">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ZjutArea",
        data(){
            return {
                tableData: [],
                areaoption:[],
                total: 0,
                pageNum: 1,
                pageSize: 10,
                id: "",
                form: {},
                isAdd:false,
                isEdit:false,
                dialogFormVisible: false,
                multipleSelection: [],
                headerBg: 'headerBg',
                roles: []
            }
        },
        created(){
            //请求分页查询数据
            this.load()
            this.area()
        },
        methods: {
            load(){
                const areaPageDto={
                    pageNum:this.pageNum,
                    pageSize:this.pageSize,
                    areaDto:{
                        id:this.id
                    }
                }
                this.request.post("/area/find",areaPageDto).then(res =>{
                    this.tableData = res.data.list
                    this.total = res.data.total
                })
            },
            save(){
                if(this.isEdit){
                    this.request.put("/area", this.form).then(res => {
                        if (res.code === '200') {
                            this.$message.success("保存成功")
                            this.dialogFormVisible = false
                            this.load()
                        }
                        else{
                            this.$message.error("保存失败")
                        }
                    })
                }else{
                    this.request.post("/area", this.form).then(res => {
                        if (res.code === '200') {
                            this.$message.success("保存成功")
                            this.dialogFormVisible = false
                            this.load()
                        }
                        else{
                            this.$message.error("保存失败")
                        }
                    })
                }
            },
            area(){
                this.request.get("/area").then(res =>{
                    this.areaoption=res.data
                })
            },
            handleAdd(){
                this.isAdd=true
                this.dialogFormVisible = true
                this.form = {}
            },
            handleEdit(row){
                this.isEdit=true
                this.form = Object.assign({}, row);
                this.dialogFormVisible = true
            },
            del(ids){
                this.request.delete("/area?ids="+ids).then(res => {
                    if (res.code === '200') {
                        this.$message.success("删除成功")
                        this.load()
                    }
                    else{
                        this.$message.error("删除失败")
                    }
                })

            },
            handleSelectionChange(){
                this.id = id
            },
            handleButtonClick(val){
                console.log(val)
                this.multipleSelection = val
            },
            delBatch() {
                console.log(this.multipleSelection.map(v => v.id));
                let ids = this.multipleSelection.map(v => v.id) //[{}, {}, {}] => [1, 2, 3]
                this.request.delete("/area?ids="+ids).then(res => {
                    if (res.code === '200') {
                        this.$message.success("批量删除成功")
                        this.load()
                    }
                    else{
                        this.$message.error("批量删除失败")
                    }
                })
            },
            reset(){
                this.id = ""
                this.load()
            },
            handleSizeChange(pageSize){
                console.log(pageSize)
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum){
                console.log(pageNum)
                this.pageNum = pageNum
                this.load()
            },
        }
    }
</script>

<style>
    .headerBg {
        background: #eee!important;
    }
</style>