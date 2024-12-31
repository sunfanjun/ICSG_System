<template>
    <div>

        <div style="margin: 10px 0">
            <span style="font-size: 13px">编号：</span>
            <el-input style="width: 200px" placeholder="请输入摄像头编号" suffix-icon="el-icon-search" v-model="id"></el-input>
            <span style="font-size: 13px; margin-left: 20px">摄像头状态：</span>
            <el-select style="width: 200px" v-model="status" placeholder="请选择">
                <el-option label="异常" value="0"></el-option>
                <el-option label="正常" value="1"></el-option>
            </el-select>
            <span style="font-size: 13px; margin-left:20px">监控区域：</span>
            <el-select v-model="aid" filterable placeholder="请选择">
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

        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"
                  @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="摄像头编号"></el-table-column>
            <el-table-column prop="time" label="开始使用时间"></el-table-column>
            <el-table-column prop="status" label="摄像头状态">
                <template slot-scope="scope">
                    <span v-if="scope.row.status== 0">异常</span>
                    <span v-if="scope.row.status== 1">正常</span>
                </template>
            </el-table-column>
            <el-table-column prop="area.name" label="监控区域"></el-table-column>
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

            <el-dialog title="监控信息" :visible.sync="dialogFormVisible" width="40%">
                <el-form label-width="100px" size="small">
                    <el-form-item label="开始使用时间">
                        <el-input v-model="form.time" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="设备状态">
                        <el-select v-model="form.status" placeholder="请选择">
                            <el-option
                                    v-for="item in statusOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="监控区域">
                        <el-select v-model="form.area.id" filterable placeholder="请选择">
                            <el-option
                                    v-for="item in areaoption"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="cancel">取 消</el-button>
                    <el-button type="primary" @click="save">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ZjutMonitor",
        data() {
            return {
                dataLoaded: false,
                tableData: [],
                areaoption: [],
                total: 0,
                pageNum: 1,
                pageSize: 10,
                id: "",
                status: "",
                time: "",
                aid: "",
                form: {
                    area:{
                        id:null
                    }
                },
                isAdd: false,
                isEdit: false,
                dialogFormVisible: false,
                multipleSelection: [],
                statusOptions: [
                    { label: '异常', value: 0 },
                    { label: '正常', value: 1 },
                ],
                headerBg: 'headerBg',
                roles: []
            }
        },
        created() {
            this.area()
            this.load()
        },
        methods: {
            load() {
                const monitorPageDto = {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    monitorDto: {
                        id: this.id,
                        status: this.status,
                        aId: this.aid
                    }
                }
                this.request.post("/monitor/find", monitorPageDto).then(res => {
                    this.tableData = res.data.list
                    this.total = res.data.total
                })
            },
            area() {
                this.request.get("/area").then(res => {
                    this.areaoption = res.data
                })
            },
            save() {
                if (this.isEdit) {
                    this.request.put("/monitor", this.form).then(res => {
                        if (res.code === '200') {
                            this.$message.success("修改成功")
                            this.dialogFormVisible = false
                            this.load()
                        } else {
                            this.$message.error("修改失败")
                        }
                    })
                } else{
                    this.request.post("/monitor", this.form).then(res => {
                        if (res.code === '200') {
                            this.$message.success("保存成功")
                            this.dialogFormVisible = false
                            this.load()
                        } else {
                            this.$message.error("保存失败")
                        }
                    })
                }
            },
            handleAdd() {
                this.dataLoaded = true
                this.isAdd = true
                this.dialogFormVisible = true
                this.form = {
                    area: {
                        id:null
                    }
                }
            },
            handleEdit(row) {
                this.dataLoaded = true
                this.isEdit = true
                const editedRow = {...row}
                this.form = editedRow
                if (editedRow.area == null){
                    this.form = {
                        id:editedRow.id,
                        time: editedRow.time,
                        status:editedRow.status,
                        area: {
                            id: null
                        }
                    }
                }
                this.dialogFormVisible = true
            },
            del(id) {
                this.request.delete("/monitor?ids=" + id).then(res => {
                    if (res.code === '200') {
                        this.$message.success("删除成功")
                        this.load()
                    } else {
                        this.$message.error("删除失败")
                    }
                })

            },
            handleSelectionChange(val) {
                console.log(val)
                this.multipleSelection = val
            },
            delBatch() {
                let ids = this.multipleSelection.map(v => v.id) //[{}, {}, {}] => [1, 2, 3]
                this.request.delete("/monitor?ids=" + ids).then(res => {
                    if (res.code === '200') {
                        this.$message.success("批量删除成功")
                        this.load()
                    } else {
                        this.$message.error("批量删除失败")
                    }
                })
            },
            cancel() {
                this.form = {
                    id: this.form.id,
                    time: this.form.time,
                    status: this.form.status,
                    area: {
                        id: this.form.aid
                    }
                };
                this.dialogFormVisible = false;
            },
            reset() {
                this.id = ""
                this.status = ""
                this.aid = ""
                this.load()
            },
            handleSizeChange(pageSize) {
                console.log(pageSize)
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum) {
                console.log(pageNum)
                this.pageNum = pageNum
                this.load()
            },
        }
    }
</script>

<style>
    .headerBg {
        background: #eee !important;
    }
</style>