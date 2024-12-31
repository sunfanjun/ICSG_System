<template>
    <div>
        <div style="margin: 10px 0">
            <span style="font-size: 13px">姓名：</span>
            <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
            <span style="font-size: 13px; margin-left: 20px">性别：</span>
            <el-select style="width: 200px" v-model="sex" placeholder="请选择">
                <el-option label="男" value="0"></el-option>
                <el-option label="女" value="1"></el-option>
            </el-select>
            <span style="font-size: 13px; margin-left:20px">管理区域：</span>
            <el-select v-model="aid" filterable placeholder="请选择">
                <el-option
                        v-for="item in areaoption"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                </el-option>
            </el-select>
            <span style="font-size: 13px; margin-left: 20px">职位：</span>
            <el-select style="width: 200px" v-model="level" placeholder="请选择">
                <el-option label="普通保安" value="普通保安"></el-option>
                <el-option label="保安队长" value="保安队长"></el-option>
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
                  @selection-change="handleButtonClick">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="编号" width="80"></el-table-column>
            <el-table-column prop="name" label="姓名" width="140"></el-table-column>
            <el-table-column prop="sex" label="性别">
                <template slot-scope="scope">
                    <span v-if="scope.row.sex== 0">男</span>
                    <span v-if="scope.row.sex== 1">女</span>
                </template>
            </el-table-column>
            <el-table-column prop="level" label="职位"></el-table-column>
            <el-table-column prop="contact" label="电话"></el-table-column>
            <el-table-column prop="area.name" label="管理区域"></el-table-column>
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

            <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="40%">
                <el-form label-width="80px" size="small">
                    <el-form-item label="姓名">
                        <el-input v-model="form.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-select v-model="form.sex" placeholder="请选择">
                            <el-option
                                    v-for="item in sexOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="职位">
                        <el-select v-model="form.level" placeholder="请选择">
                            <el-option label="普通保安" value="普通保安"></el-option>
                            <el-option label="保安队长" value="保安队长"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="电话">
                        <el-input v-model="form.contact" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="管理区域">
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
        name: "ZjutClient",
        data() {
            return {
                dataLoaded: false,
                tableData: [],
                areaoption: [],
                total: 0,
                pageNum: 1,
                pageSize: 10,
                name: "",
                contact: "",
                aid: "",
                sex: "",
                level: "",
                form: {
                    area: {
                        id: null
                    }
                },
                isAdd: false,
                isEdit: false,
                dialogFormVisible: false,
                multipleSelection: [],
                headerBg: 'headerBg',
                sexOptions: [
                    {label: '男', value: 0},
                    {label: '女', value: 1},
                ],
                roles: []
            }
        },
        created() {
            //请求分页查询数据
            this.load()
            this.area()
        },
        methods: {
            load() {
                const securityGuardPageDto = {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    securityGuardDto: {
                        name: this.name,
                        sex: this.sex,
                        level: this.level,
                        aId: this.aid
                    }
                }
                this.request.post("/securityGuard/find", securityGuardPageDto).then(res => {
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
                    this.request.put("/securityGuard", this.form).then(res => {
                        if (res.code === '200') {
                            this.$message.success("修改成功")
                            this.dialogFormVisible = false
                            this.load()
                        } else {
                            this.$message.error("修改失败")
                        }
                    })
                } else {
                    this.request.post("/securityGuard", this.form).then(res => {
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
                this.isAdd = true
                this.dialogFormVisible = true
                this.form = {
                    area: {
                        id: null
                    }
                }
            },
            handleEdit(row) {
                console.log(row.area)
                this.isEdit = true
                this.dataLoaded = true
                const editedRow = {...row}
                this.form = editedRow
                if (editedRow.area == null){
                    this.form = {
                        id:editedRow.id,
                        sex: editedRow.sex,
                        name:editedRow.name,
                        level:editedRow.level,
                        contact:editedRow.contact,
                        area: {
                            id: null
                        }
                    }
                }
                this.dialogFormVisible = true
            },
            cancel() {
                this.form = {
                    sex: this.form.sex,
                    name: this.form.name,
                    level: this.form.level,
                    area: {
                        id: this.form.aid
                    }
                };
                this.dialogFormVisible = false;
            },
            del(id) {
                this.request.delete("/securityGuard?ids=" + id).then(res => {
                    if (res.code === '200') {
                        this.$message.success("删除成功")
                        this.load()
                    } else {
                        this.$message.error("删除失败")
                    }
                })

            },
            handleButtonClick(val) {
                console.log(val)
                this.multipleSelection = val
            },
            delBatch() {
                let ids = this.multipleSelection.map(v => v.id) //[{}, {}, {}] => [1, 2, 3]
                this.request.delete("/securityGuard?ids=" + ids).then(res => {
                    if (res.code === '200') {
                        this.$message.success("批量删除成功")
                        this.load()
                    } else {
                        this.$message.error("批量删除失败")
                    }
                })
            },
            reset() {
                this.name = ""
                this.sex = ""
                this.level = ""
                this.aid = ""
                this.load()
            },
            handleSizeChange(pageSize) {
                // console.log(pageSize)
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum) {
                // console.log(pageNum)
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