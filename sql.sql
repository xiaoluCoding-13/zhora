-- ----------------------------
-- Table structure for ai_llm_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."ai_llm_config";
CREATE TABLE "public"."ai_llm_config" (
  "id" int8 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "code" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "model_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "type" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "api_key" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "url" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "enable" bool NOT NULL DEFAULT true,
  "priority" int2 NOT NULL,
  "create_by" varchar(64) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(64) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "del_flag" bool NOT NULL DEFAULT false
)
;
ALTER TABLE "public"."ai_llm_config" OWNER TO "zhora";
COMMENT ON COLUMN "public"."ai_llm_config"."id" IS 'id';
COMMENT ON COLUMN "public"."ai_llm_config"."name" IS '模型厂家';
COMMENT ON COLUMN "public"."ai_llm_config"."code" IS '模型code';
COMMENT ON COLUMN "public"."ai_llm_config"."model_name" IS '模型名称';
COMMENT ON COLUMN "public"."ai_llm_config"."type" IS '模型类型';
COMMENT ON COLUMN "public"."ai_llm_config"."api_key" IS '模型秘钥';
COMMENT ON COLUMN "public"."ai_llm_config"."url" IS '模型base地址';
COMMENT ON COLUMN "public"."ai_llm_config"."enable" IS '是否禁用';
COMMENT ON COLUMN "public"."ai_llm_config"."priority" IS '优先级,数字越大优先级越高';
COMMENT ON COLUMN "public"."ai_llm_config"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."ai_llm_config"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."ai_llm_config"."update_by" IS '更新人';
COMMENT ON COLUMN "public"."ai_llm_config"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."ai_llm_config"."del_flag" IS '删除标记,true:已删除,false:正常';
COMMENT ON TABLE "public"."ai_llm_config" IS '模型配置';

-- ----------------------------
-- Records of ai_llm_config
-- ----------------------------
BEGIN;
INSERT INTO "public"."ai_llm_config" ("id", "name", "code", "model_name", "type", "api_key", "url", "enable", "priority", "create_by", "create_time", "update_by", "update_time", "del_flag") VALUES (1, 'DeepSeek', 'DEEP_SEEK', 'deepseek-chat', 'CHAT', '', 'https://api.deepseek.com', 'f', 0, NULL, NULL, NULL, NULL, 'f');
INSERT INTO "public"."ai_llm_config" ("id", "name", "code", "model_name", "type", "api_key", "url", "enable", "priority", "create_by", "create_time", "update_by", "update_time", "del_flag") VALUES (2, 'Qwen', 'Q_WEN', 'qwen3-32b', 'CHAT', '', 'https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation', 't', 1, NULL, NULL, NULL, NULL, 'f');
INSERT INTO "public"."ai_llm_config" ("id", "name", "code", "model_name", "type", "api_key", "url", "enable", "priority", "create_by", "create_time", "update_by", "update_time", "del_flag") VALUES (3, 'Qwen_embedding', 'Q_WEN_EMBEDDING', 'text-embedding-v4', 'EMBEDDING', '', 'https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation', 't', 2, NULL, NULL, NULL, NULL, 'f');
COMMIT;

-- ----------------------------
-- Primary Key structure for table ai_llm_config
-- ----------------------------
ALTER TABLE "public"."ai_llm_config" ADD CONSTRAINT "ai_config_pkey" PRIMARY KEY ("id");

CREATE SCHEMA IF NOT EXISTS zhora;

DROP TABLE IF EXISTS "public"."qwen_embedding_store";
CREATE TABLE "public"."qwen_embedding_store" (
    "embedding_id" uuid NOT NULL,
    "embedding" "public"."vector",
    "text" text COLLATE "pg_catalog"."default",
    "metadata" json,
    "create_by" varchar(64) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by" varchar(64) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    "del_flag" bool DEFAULT false
)
;
ALTER TABLE "public"."qwen_embedding_store" OWNER TO "zhora";
COMMENT ON COLUMN "public"."qwen_embedding_store"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."qwen_embedding_store"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."qwen_embedding_store"."update_by" IS '更新人';
COMMENT ON COLUMN "public"."qwen_embedding_store"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."qwen_embedding_store"."del_flag" IS '删除标记,true:已删除,false:正常';

-- ----------------------------
-- Table structure for qwen_library_embedding_store
-- ----------------------------
DROP TABLE IF EXISTS "public"."qwen_library_embedding_store";
CREATE TABLE "public"."qwen_library_embedding_store" (
    "embedding_id" uuid NOT NULL,
    "embedding" "public"."vector",
    "text" text COLLATE "pg_catalog"."default",
    "metadata" json,
    "create_by" varchar(64) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by" varchar(64) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    "del_flag" bool DEFAULT false
)
;
ALTER TABLE "public"."qwen_library_embedding_store" OWNER TO "zhora";
COMMENT ON COLUMN "public"."qwen_library_embedding_store"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."qwen_library_embedding_store"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."qwen_library_embedding_store"."update_by" IS '更新人';
COMMENT ON COLUMN "public"."qwen_library_embedding_store"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."qwen_library_embedding_store"."del_flag" IS '删除标记,true:已删除,false:正常';

-- ----------------------------
-- Primary Key structure for table qwen_embedding_store
-- ----------------------------
ALTER TABLE "public"."qwen_embedding_store" ADD CONSTRAINT "qwen_embedding_store_pkey" PRIMARY KEY ("embedding_id");

-- ----------------------------
-- Primary Key structure for table qwen_library_embedding_store
-- ----------------------------
ALTER TABLE "public"."qwen_library_embedding_store" ADD CONSTRAINT "qwen_library_embedding_store_pkey" PRIMARY KEY ("embedding_id");

-- ----------------------------
-- 1、部门表
-- ----------------------------
CREATE TABLE "zhora"."sys_dept" (
    "dept_id" int8 NOT NULL,
    "parent_id" int8 NOT NULL DEFAULT 0,
    "ancestors" varchar(50) COLLATE "pg_catalog"."default",
    "dept_name" varchar(50) COLLATE "pg_catalog"."default",
    "order_num" int4 DEFAULT 0,
    "leader" varchar(20) COLLATE "pg_catalog"."default",
    "phone" varchar(11) COLLATE "pg_catalog"."default",
    "email" varchar(50) COLLATE "pg_catalog"."default",
    "status" char(1) COLLATE "pg_catalog"."default",
    "del_flag" bool DEFAULT false,
    "create_by" varchar(64) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by" varchar(64) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    CONSTRAINT "sys_dept_pkey" PRIMARY KEY ("dept_id")
)
;

ALTER TABLE "zhora"."sys_dept"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_dept"."dept_id" IS '部门id';

COMMENT ON COLUMN "zhora"."sys_dept"."parent_id" IS '父部门id';

COMMENT ON COLUMN "zhora"."sys_dept"."ancestors" IS '祖级列表';

COMMENT ON COLUMN "zhora"."sys_dept"."dept_name" IS '部门名称';

COMMENT ON COLUMN "zhora"."sys_dept"."order_num" IS '显示顺序';

COMMENT ON COLUMN "zhora"."sys_dept"."leader" IS '负责人';

COMMENT ON COLUMN "zhora"."sys_dept"."phone" IS '联系电话';

COMMENT ON COLUMN "zhora"."sys_dept"."email" IS '邮箱';

COMMENT ON COLUMN "zhora"."sys_dept"."status" IS '部门状态（0正常 1停用）';

COMMENT ON COLUMN "zhora"."sys_dept"."del_flag" IS '删除标记,true:已删除,false:正常';

COMMENT ON COLUMN "zhora"."sys_dept"."create_by" IS '创建人';

COMMENT ON COLUMN "zhora"."sys_dept"."create_time" IS '创建时间';

COMMENT ON COLUMN "zhora"."sys_dept"."update_by" IS '更新人';

COMMENT ON COLUMN "zhora"."sys_dept"."update_time" IS '更新时间';

COMMENT ON TABLE "zhora"."sys_dept" IS '部门表';
-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
INSERT INTO "zhora"."sys_dept" ("dept_id", "parent_id", "ancestors", "dept_name", "order_num", "leader", "phone", "email", "status", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', FALSE, 'admin', '2025-08-19 08:23:23.83621', '', NULL);
INSERT INTO "zhora"."sys_dept" ("dept_id", "parent_id", "ancestors", "dept_name", "order_num", "leader", "phone", "email", "status", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', FALSE, 'admin', '2025-08-19 08:23:23.840647', '', NULL);
INSERT INTO "zhora"."sys_dept" ("dept_id", "parent_id", "ancestors", "dept_name", "order_num", "leader", "phone", "email", "status", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', FALSE, 'admin', '2025-08-19 08:23:23.841963', '', NULL);
INSERT INTO "zhora"."sys_dept" ("dept_id", "parent_id", "ancestors", "dept_name", "order_num", "leader", "phone", "email", "status", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', FALSE, 'admin', '2025-08-19 08:23:23.843171', '', NULL);
INSERT INTO "zhora"."sys_dept" ("dept_id", "parent_id", "ancestors", "dept_name", "order_num", "leader", "phone", "email", "status", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', FALSE, 'admin', '2025-08-19 08:23:23.844293', '', NULL);
INSERT INTO "zhora"."sys_dept" ("dept_id", "parent_id", "ancestors", "dept_name", "order_num", "leader", "phone", "email", "status", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', FALSE, 'admin', '2025-08-19 08:23:23.845269', '', NULL);
INSERT INTO "zhora"."sys_dept" ("dept_id", "parent_id", "ancestors", "dept_name", "order_num", "leader", "phone", "email", "status", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', FALSE, 'admin', '2025-08-19 08:23:23.846263', '', NULL);
INSERT INTO "zhora"."sys_dept" ("dept_id", "parent_id", "ancestors", "dept_name", "order_num", "leader", "phone", "email", "status", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', FALSE, 'admin', '2025-08-19 08:23:23.847249', '', NULL);
INSERT INTO "zhora"."sys_dept" ("dept_id", "parent_id", "ancestors", "dept_name", "order_num", "leader", "phone", "email", "status", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', FALSE, 'admin', '2025-08-19 08:23:23.848175', '', NULL);
INSERT INTO "zhora"."sys_dept" ("dept_id", "parent_id", "ancestors", "dept_name", "order_num", "leader", "phone", "email", "status", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', FALSE, 'admin', '2025-08-19 08:23:23.849129', '', NULL);

-- ----------------------------
-- 2、用户信息表
-- ----------------------------
CREATE TABLE "zhora"."sys_user" (
    "user_id" int8 NOT NULL,
    "dept_id" int8 NOT NULL,
    "login_name" varchar(30) COLLATE "pg_catalog"."default",
    "user_name" varchar(30) COLLATE "pg_catalog"."default",
    "user_type" varchar(2) COLLATE "pg_catalog"."default" DEFAULT 0,
    "email" varchar(50) COLLATE "pg_catalog"."default",
    "phonenumber" varchar(11) COLLATE "pg_catalog"."default",
    "sex" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
    "avatar" varchar(100) COLLATE "pg_catalog"."default",
    "password" varchar(50) COLLATE "pg_catalog"."default",
    "salt" varchar(20) COLLATE "pg_catalog"."default",
    "status" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
    "login_ip" varchar(128) COLLATE "pg_catalog"."default",
    "login_date" timestamp(6),
    "pwd_update_date" timestamp(6),
    "remark" varchar(500) COLLATE "pg_catalog"."default",
    "del_flag" bool DEFAULT false,
    "create_by" varchar(64) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by" varchar(64) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    CONSTRAINT "sys_user_pkey" PRIMARY KEY ("user_id")
)
;

ALTER TABLE "zhora"."sys_user"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_user"."user_id" IS '用户ID';

COMMENT ON COLUMN "zhora"."sys_user"."dept_id" IS '部门ID';

COMMENT ON COLUMN "zhora"."sys_user"."login_name" IS '登录账号';

COMMENT ON COLUMN "zhora"."sys_user"."user_name" IS '用户昵称';

COMMENT ON COLUMN "zhora"."sys_user"."user_type" IS '用户类型（00系统用户 01注册用户）';

COMMENT ON COLUMN "zhora"."sys_user"."email" IS '用户邮箱';

COMMENT ON COLUMN "zhora"."sys_user"."phonenumber" IS '手机号码';

COMMENT ON COLUMN "zhora"."sys_user"."sex" IS '用户性别（0男 1女 2未知）';

COMMENT ON COLUMN "zhora"."sys_user"."avatar" IS '头像路径';

COMMENT ON COLUMN "zhora"."sys_user"."password" IS '密码';

COMMENT ON COLUMN "zhora"."sys_user"."salt" IS '盐加密';

COMMENT ON COLUMN "zhora"."sys_user"."status" IS '账号状态（0正常 1停用）';

COMMENT ON COLUMN "zhora"."sys_user"."login_ip" IS '最后登录IP';

COMMENT ON COLUMN "zhora"."sys_user"."login_date" IS '最后登录时间';

COMMENT ON COLUMN "zhora"."sys_user"."pwd_update_date" IS '密码最后更新时间';

COMMENT ON COLUMN "zhora"."sys_user"."remark" IS '备注';

COMMENT ON COLUMN "zhora"."sys_user"."del_flag" IS '删除标记,true:已删除,false:正常';

COMMENT ON COLUMN "zhora"."sys_user"."create_by" IS '创建人';

COMMENT ON COLUMN "zhora"."sys_user"."create_time" IS '创建时间';

COMMENT ON COLUMN "zhora"."sys_user"."update_by" IS '更新人';

COMMENT ON COLUMN "zhora"."sys_user"."update_time" IS '更新时间';

COMMENT ON TABLE "zhora"."sys_user" IS '用户信息表';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user values(1,  103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '29c67a30398638269fe600f73a054934', '111111', '0', '127.0.0.1', null, null, '管理员',FALSE, 'admin', clock_timestamp(), '', null);
insert into sys_user values(2,  105, 'ry',    '若依', '00', 'ry@qq.com',  '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '127.0.0.1', null, null, '测试员',FALSE, 'admin', clock_timestamp(), '', null);

-- ----------------------------
-- 3、岗位信息表
-- ----------------------------
CREATE TABLE "zhora"."sys_post" (
    "post_id" int8 NOT NULL,
    "post_code" varchar(64) COLLATE "pg_catalog"."default",
    "post_name" varchar(50) COLLATE "pg_catalog"."default",
    "post_sort" int2,
    "status" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
    "del_flag" varchar(2) COLLATE "pg_catalog"."default" DEFAULT '0'::character varying,
    "create_by" varchar(64) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by" varchar(64) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    CONSTRAINT "sys_post_pkey" PRIMARY KEY ("post_id")
)
;

ALTER TABLE "zhora"."sys_post"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_post"."post_id" IS '岗位ID';

COMMENT ON COLUMN "zhora"."sys_post"."post_code" IS '岗位编码';

COMMENT ON COLUMN "zhora"."sys_post"."post_name" IS '岗位名称';

COMMENT ON COLUMN "zhora"."sys_post"."post_sort" IS '显示顺序';

COMMENT ON COLUMN "zhora"."sys_post"."status" IS '状态（0正常 1停用）';

COMMENT ON COLUMN "zhora"."sys_post"."del_flag" IS '删除标记,1:已删除,0:正常';

COMMENT ON COLUMN "zhora"."sys_post"."create_by" IS '创建人';

COMMENT ON COLUMN "zhora"."sys_post"."create_time" IS '创建时间';

COMMENT ON COLUMN "zhora"."sys_post"."update_by" IS '更新人';

COMMENT ON COLUMN "zhora"."sys_post"."update_time" IS '更新时间';

COMMENT ON TABLE "zhora"."sys_post" IS '岗位信息表';

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------
insert into sys_post values(1, 'ceo',  '董事长',    1, '0', '0','admin', clock_timestamp(), '', null);
insert into sys_post values(2, 'se',   '项目经理',  2, '0','0', 'admin', clock_timestamp(), '', null);
insert into sys_post values(3, 'hr',   '人力资源',  3, '0','0', 'admin', clock_timestamp(), '', null);
insert into sys_post values(4, 'user', '普通员工',  4, '0','0', 'admin', clock_timestamp(), '', null);


-- ----------------------------
-- 4、角色信息表
-- ----------------------------
CREATE TABLE "zhora"."sys_role" (
    "role_id" int8 NOT NULL,
    "role_name" varchar(30) COLLATE "pg_catalog"."default",
    "role_key" varchar(100) COLLATE "pg_catalog"."default",
    "role_sort" int2,
    "data_scope" char(1) COLLATE "pg_catalog"."default" DEFAULT 1,
    "status" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
    "remark" varchar(500) COLLATE "pg_catalog"."default",
    "del_flag" bool DEFAULT false,
    "create_by" varchar(64) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by" varchar(64) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    CONSTRAINT "sys_role_pkey" PRIMARY KEY ("role_id")
)
;

ALTER TABLE "zhora"."sys_role"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_role"."role_id" IS '角色ID';

COMMENT ON COLUMN "zhora"."sys_role"."role_name" IS '角色名称';

COMMENT ON COLUMN "zhora"."sys_role"."role_key" IS '角色权限字符串';

COMMENT ON COLUMN "zhora"."sys_role"."role_sort" IS '显示顺序';

COMMENT ON COLUMN "zhora"."sys_role"."data_scope" IS '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）';

COMMENT ON COLUMN "zhora"."sys_role"."status" IS '角色状态（0正常 1停用）';

COMMENT ON COLUMN "zhora"."sys_role"."remark" IS '备注';

COMMENT ON COLUMN "zhora"."sys_role"."del_flag" IS '删除标记,true:已删除,false:正常';

COMMENT ON COLUMN "zhora"."sys_role"."create_by" IS '创建人';

COMMENT ON COLUMN "zhora"."sys_role"."create_time" IS '创建时间';

COMMENT ON COLUMN "zhora"."sys_role"."update_by" IS '更新人';

COMMENT ON COLUMN "zhora"."sys_role"."update_time" IS '更新时间';

COMMENT ON TABLE "zhora"."sys_role" IS '角色信息表';

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
INSERT INTO "zhora"."sys_role" ("role_id", "role_name", "role_key", "role_sort", "data_scope", "status", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (1, '超级管理员', 'admin', 1, '1', '0', '超级管理员', false, 'admin', '2025-08-19 08:51:32.47536', '', NULL);
INSERT INTO "zhora"."sys_role" ("role_id", "role_name", "role_key", "role_sort", "data_scope", "status", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (2, '普通角色', 'common', 2, '2', '0', '普通角色', false, 'admin', '2025-08-19 08:51:32.477144', '', NULL);

-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
CREATE TABLE "zhora"."sys_menu" (
                                    "menu_id" int8 NOT NULL,
                                    "menu_name" varchar(50) COLLATE "pg_catalog"."default",
                                    "parent_id" int8 NOT NULL,
                                    "order_num" int4 DEFAULT 0,
                                    "url" varchar(200) COLLATE "pg_catalog"."default",
                                    "target" varchar(20) COLLATE "pg_catalog"."default",
                                    "menu_type" char(1) COLLATE "pg_catalog"."default",
                                    "visible" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
                                    "is_refresh" char(1) COLLATE "pg_catalog"."default" DEFAULT 1,
                                    "perms" varchar(100) COLLATE "pg_catalog"."default",
                                    "icon" varchar(100) COLLATE "pg_catalog"."default",
                                    "remark" varchar(500) COLLATE "pg_catalog"."default",
                                    "del_flag" bool DEFAULT false,
                                    "create_by" varchar(64) COLLATE "pg_catalog"."default",
                                    "create_time" timestamp(6),
                                    "update_by" varchar(64) COLLATE "pg_catalog"."default",
                                    "update_time" timestamp(6),
                                    CONSTRAINT "sys_menu_pkey" PRIMARY KEY ("menu_id")
)
;

ALTER TABLE "zhora"."sys_menu"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_menu"."menu_id" IS '菜单ID';

COMMENT ON COLUMN "zhora"."sys_menu"."menu_name" IS '菜单名称';

COMMENT ON COLUMN "zhora"."sys_menu"."parent_id" IS '父菜单ID';

COMMENT ON COLUMN "zhora"."sys_menu"."order_num" IS '显示顺序';

COMMENT ON COLUMN "zhora"."sys_menu"."url" IS '请求地址';

COMMENT ON COLUMN "zhora"."sys_menu"."target" IS '打开方式（menuItem页签 menuBlank新窗口）';

COMMENT ON COLUMN "zhora"."sys_menu"."menu_type" IS '菜单类型（M目录 C菜单 F按钮）';

COMMENT ON COLUMN "zhora"."sys_menu"."visible" IS '菜单状态（0显示 1隐藏）';

COMMENT ON COLUMN "zhora"."sys_menu"."is_refresh" IS '是否刷新（0刷新 1不刷新）';

COMMENT ON COLUMN "zhora"."sys_menu"."perms" IS '权限标识';

COMMENT ON COLUMN "zhora"."sys_menu"."icon" IS '菜单图标';

COMMENT ON COLUMN "zhora"."sys_menu"."remark" IS '备注';

COMMENT ON COLUMN "zhora"."sys_menu"."del_flag" IS '删除标记,true:已删除,false:正常';

COMMENT ON COLUMN "zhora"."sys_menu"."create_by" IS '创建人';

COMMENT ON COLUMN "zhora"."sys_menu"."create_time" IS '创建时间';

COMMENT ON COLUMN "zhora"."sys_menu"."update_by" IS '更新人';

COMMENT ON COLUMN "zhora"."sys_menu"."update_time" IS '更新时间';

COMMENT ON TABLE "zhora"."sys_menu" IS '菜单权限表';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu values('1', '系统管理', '0', '1', '#',                '',          'M', '0', '1', '', 'fa fa-gear',          '系统管理目录',false ,'admin', clock_timestamp(), '', null);
insert into sys_menu values('2', '系统监控', '0', '2', '#',                '',          'M', '0', '1', '', 'fa fa-video-camera',   '系统监控目录',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('3', '系统工具', '0', '3', '#',                '',          'M', '0', '1', '', 'fa fa-bars',          '系统工具目录',false, 'admin', clock_timestamp(), '', null);
insert into sys_menu values('4', '若依官网', '0', '4', 'http://ruoyi.vip', 'menuBlank', 'C', '0', '1', '', 'fa fa-location-arrow', '若依官网地址',false,'admin', clock_timestamp(), '', null);
-- 二级菜单
insert into sys_menu values('100',  '用户管理', '1', '1', '/system/user',          '', 'C', '0', '1', 'system:user:view',         'fa fa-user-o',          '用户管理菜单',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('101',  '角色管理', '1', '2', '/system/role',          '', 'C', '0', '1', 'system:role:view',         'fa fa-user-secret',    '角色管理菜单',false, 'admin', clock_timestamp(), '', null);
insert into sys_menu values('102',  '菜单管理', '1', '3', '/system/menu',          '', 'C', '0', '1', 'system:menu:view',         'fa fa-th-list',        '菜单管理菜单',false, 'admin', clock_timestamp(), '', null );
insert into sys_menu values('103',  '部门管理', '1', '4', '/system/dept',          '', 'C', '0', '1', 'system:dept:view',         'fa fa-outdent',         '部门管理菜单',false,'admin', clock_timestamp(), '', null );
insert into sys_menu values('104',  '岗位管理', '1', '5', '/system/post',          '', 'C', '0', '1', 'system:post:view',         'fa fa-address-card-o',  '岗位管理菜单',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('105',  '字典管理', '1', '6', '/system/dict',          '', 'C', '0', '1', 'system:dict:view',         'fa fa-bookmark-o',      '字典管理菜单',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('106',  '参数设置', '1', '7', '/system/config',        '', 'C', '0', '1', 'system:config:view',       'fa fa-sun-o',          '参数设置菜单',false, 'admin', clock_timestamp(), '', null );
insert into sys_menu values('107',  '通知公告', '1', '8', '/system/notice',        '', 'C', '0', '1', 'system:notice:view',       'fa fa-bullhorn',        '通知公告菜单',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('108',  '日志管理', '1', '9', '#',                     '', 'M', '0', '1', '',                         'fa fa-pencil-square-o','日志管理菜单',false ,'admin', clock_timestamp(), '', null);
insert into sys_menu values('109',  '在线用户', '2', '1', '/monitor/online',       '', 'C', '0', '1', 'monitor:online:view',      'fa fa-user-circle',     '在线用户菜单',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('110',  '定时任务', '2', '2', '/monitor/job',          '', 'C', '0', '1', 'monitor:job:view',         'fa fa-tasks',           '定时任务菜单',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('111',  '数据监控', '2', '3', '/monitor/data',         '', 'C', '0', '1', 'monitor:data:view',        'fa fa-bug',            '数据监控菜单',false ,'admin', clock_timestamp(), '', null);
insert into sys_menu values('112',  '服务监控', '2', '4', '/monitor/server',       '', 'C', '0', '1', 'monitor:server:view',      'fa fa-server',          '服务监控菜单',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('113',  '缓存监控', '2', '5', '/monitor/cache',        '', 'C', '0', '1', 'monitor:cache:view',       'fa fa-cube',          '缓存监控菜单',false,  'admin', clock_timestamp(), '', null);
insert into sys_menu values('114',  '表单构建', '3', '1', '/tool/build',           '', 'C', '0', '1', 'tool:build:view',          'fa fa-wpforms',      '表单构建菜单',false,   'admin', clock_timestamp(), '', null);
insert into sys_menu values('115',  '代码生成', '3', '2', '/tool/gen',             '', 'C', '0', '1', 'tool:gen:view',            'fa fa-code',           '代码生成菜单',false, 'admin', clock_timestamp(), '', null);
insert into sys_menu values('116',  '系统接口', '3', '3', '/tool/swagger',         '', 'C', '0', '1', 'tool:swagger:view',        'fa fa-gg',             '系统接口菜单',false, 'admin', clock_timestamp(), '', null);
-- 三级菜单
insert into sys_menu values('500',  '操作日志', '108', '1', '/monitor/operlog',    '', 'C', '0', '1', 'monitor:operlog:view',     'fa fa-address-book',    '操作日志菜单',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('501',  '登录日志', '108', '2', '/monitor/logininfor', '', 'C', '0', '1', 'monitor:logininfor:view',  'fa fa-file-image-o',    '登录日志菜单',false,'admin', clock_timestamp(), '', null);
-- 用户管理按钮
insert into sys_menu values('1000', '用户查询', '100', '1',  '#', '',  'F', '0', '1', 'system:user:list',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1001', '用户新增', '100', '2',  '#', '',  'F', '0', '1', 'system:user:add',         '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1002', '用户修改', '100', '3',  '#', '',  'F', '0', '1', 'system:user:edit',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1003', '用户删除', '100', '4',  '#', '',  'F', '0', '1', 'system:user:remove',      '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1004', '用户导出', '100', '5',  '#', '',  'F', '0', '1', 'system:user:export',      '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1005', '用户导入', '100', '6',  '#', '',  'F', '0', '1', 'system:user:import',      '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1006', '重置密码', '100', '7',  '#', '',  'F', '0', '1', 'system:user:resetPwd',    '#', '',false,'admin', clock_timestamp(), '', null);
-- 角色管理按钮
insert into sys_menu values('1007', '角色查询', '101', '1',  '#', '',  'F', '0', '1', 'system:role:list',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1008', '角色新增', '101', '2',  '#', '',  'F', '0', '1', 'system:role:add',         '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1009', '角色修改', '101', '3',  '#', '',  'F', '0', '1', 'system:role:edit',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1010', '角色删除', '101', '4',  '#', '',  'F', '0', '1', 'system:role:remove',      '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1011', '角色导出', '101', '5',  '#', '',  'F', '0', '1', 'system:role:export',      '#', '',false,'admin', clock_timestamp(), '', null);
-- 菜单管理按钮
insert into sys_menu values('1012', '菜单查询', '102', '1',  '#', '',  'F', '0', '1', 'system:menu:list',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1013', '菜单新增', '102', '2',  '#', '',  'F', '0', '1', 'system:menu:add',         '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1014', '菜单修改', '102', '3',  '#', '',  'F', '0', '1', 'system:menu:edit',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1015', '菜单删除', '102', '4',  '#', '',  'F', '0', '1', 'system:menu:remove',      '#', '',false,'admin', clock_timestamp(), '', null);
-- 部门管理按钮
insert into sys_menu values('1016', '部门查询', '103', '1',  '#', '',  'F', '0', '1', 'system:dept:list',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1017', '部门新增', '103', '2',  '#', '',  'F', '0', '1', 'system:dept:add',         '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1018', '部门修改', '103', '3',  '#', '',  'F', '0', '1', 'system:dept:edit',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1019', '部门删除', '103', '4',  '#', '',  'F', '0', '1', 'system:dept:remove',      '#', '',false,'admin', clock_timestamp(), '', null);
-- 岗位管理按钮
insert into sys_menu values('1020', '岗位查询', '104', '1',  '#', '',  'F', '0', '1', 'system:post:list',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1021', '岗位新增', '104', '2',  '#', '',  'F', '0', '1', 'system:post:add',         '#','', false, 'admin', clock_timestamp(), '', null);
insert into sys_menu values('1022', '岗位修改', '104', '3',  '#', '',  'F', '0', '1', 'system:post:edit',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1023', '岗位删除', '104', '4',  '#', '',  'F', '0', '1', 'system:post:remove',      '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1024', '岗位导出', '104', '5',  '#', '',  'F', '0', '1', 'system:post:export',      '#', '',false,'admin', clock_timestamp(), '', null);
-- 字典管理按钮
insert into sys_menu values('1025', '字典查询', '105', '1',  '#', '',  'F', '0', '1', 'system:dict:list',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1026', '字典新增', '105', '2',  '#', '',  'F', '0', '1', 'system:dict:add',         '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1027', '字典修改', '105', '3',  '#', '',  'F', '0', '1', 'system:dict:edit',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1028', '字典删除', '105', '4',  '#', '',  'F', '0', '1', 'system:dict:remove',      '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1029', '字典导出', '105', '5',  '#', '',  'F', '0', '1', 'system:dict:export',      '#', '',false,'admin', clock_timestamp(), '', null);
-- 参数设置按钮
insert into sys_menu values('1030', '参数查询', '106', '1',  '#', '',  'F', '0', '1', 'system:config:list',      '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1031', '参数新增', '106', '2',  '#', '',  'F', '0', '1', 'system:config:add',       '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1032', '参数修改', '106', '3',  '#', '',  'F', '0', '1', 'system:config:edit',      '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1033', '参数删除', '106', '4',  '#', '',  'F', '0', '1', 'system:config:remove',    '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1034', '参数导出', '106', '5',  '#', '',  'F', '0', '1', 'system:config:export',    '#', '',false,'admin', clock_timestamp(), '', null);
-- 通知公告按钮
insert into sys_menu values('1035', '公告查询', '107', '1',  '#', '',  'F', '0', '1', 'system:notice:list',      '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1036', '公告新增', '107', '2',  '#', '',  'F', '0', '1', 'system:notice:add',       '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1037', '公告修改', '107', '3',  '#', '',  'F', '0', '1', 'system:notice:edit',      '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1038', '公告删除', '107', '4',  '#', '',  'F', '0', '1', 'system:notice:remove',    '#', '',false,'admin', clock_timestamp(), '', null);
-- 操作日志按钮
insert into sys_menu values('1039', '操作查询', '500', '1',  '#', '',  'F', '0', '1', 'monitor:operlog:list',    '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1040', '操作删除', '500', '2',  '#', '',  'F', '0', '1', 'monitor:operlog:remove',  '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1041', '详细信息', '500', '3',  '#', '',  'F', '0', '1', 'monitor:operlog:detail',  '#','', false, 'admin', clock_timestamp(), '', null);
insert into sys_menu values('1042', '日志导出', '500', '4',  '#', '',  'F', '0', '1', 'monitor:operlog:export',  '#', '',false,'admin', clock_timestamp(), '', null);
-- 登录日志按钮
insert into sys_menu values('1043', '登录查询', '501', '1',  '#', '',  'F', '0', '1', 'monitor:logininfor:list',         '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1044', '登录删除', '501', '2',  '#', '',  'F', '0', '1', 'monitor:logininfor:remove',       '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1045', '日志导出', '501', '3',  '#', '',  'F', '0', '1', 'monitor:logininfor:export',       '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1046', '账户解锁', '501', '4',  '#', '',  'F', '0', '1', 'monitor:logininfor:unlock',       '#', '',false,'admin', clock_timestamp(), '', null);
-- 在线用户按钮
insert into sys_menu values('1047', '在线查询', '109', '1',  '#', '',  'F', '0', '1', 'monitor:online:list',             '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1048', '批量强退', '109', '2',  '#', '',  'F', '0', '1', 'monitor:online:batchForceLogout', '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1049', '单条强退', '109', '3',  '#', '',  'F', '0', '1', 'monitor:online:forceLogout',      '#', '',false,'admin', clock_timestamp(), '', null);
-- 定时任务按钮
insert into sys_menu values('1050', '任务查询', '110', '1',  '#', '',  'F', '0', '1', 'monitor:job:list',                '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1051', '任务新增', '110', '2',  '#', '',  'F', '0', '1', 'monitor:job:add',                 '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1052', '任务修改', '110', '3',  '#', '',  'F', '0', '1', 'monitor:job:edit',                '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1053', '任务删除', '110', '4',  '#', '',  'F', '0', '1', 'monitor:job:remove',              '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1054', '状态修改', '110', '5',  '#', '',  'F', '0', '1', 'monitor:job:changeStatus',        '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1055', '任务详细', '110', '6',  '#', '',  'F', '0', '1', 'monitor:job:detail',              '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1056', '任务导出', '110', '7',  '#', '',  'F', '0', '1', 'monitor:job:export',              '#', '',false,'admin', clock_timestamp(), '', null);
-- 代码生成按钮
insert into sys_menu values('1057', '生成查询', '115', '1',  '#', '',  'F', '0', '1', 'tool:gen:list',     '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1058', '生成修改', '115', '2',  '#', '',  'F', '0', '1', 'tool:gen:edit',     '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1059', '生成删除', '115', '3',  '#', '',  'F', '0', '1', 'tool:gen:remove',   '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1060', '预览代码', '115', '4',  '#', '',  'F', '0', '1', 'tool:gen:preview',  '#', '',false,'admin', clock_timestamp(), '', null);
insert into sys_menu values('1061', '生成代码', '115', '5',  '#', '',  'F', '0', '1', 'tool:gen:code',     '#', '',false,'admin', clock_timestamp(), '', null);

-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
CREATE TABLE "zhora"."sys_user_role" (
     "user_id" int8 NOT NULL,
     "role_id" int8 NOT NULL,
     CONSTRAINT "sys_user_role_pkey" PRIMARY KEY ("user_id", "role_id")
)
;

ALTER TABLE "zhora"."sys_user_role"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_user_role"."user_id" IS '用户ID';

COMMENT ON COLUMN "zhora"."sys_user_role"."role_id" IS '角色ID';

COMMENT ON TABLE "zhora"."sys_user_role" IS '用户和角色关联表';

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role values ('1', '1');
insert into sys_user_role values ('2', '2');


-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
CREATE TABLE "zhora"."sys_role_menu" (
     "role_id" int8 NOT NULL,
     "menu_id" int8 NOT NULL,
     CONSTRAINT "sys_role_menu_pkey" PRIMARY KEY ("role_id", "menu_id")
)
;

ALTER TABLE "zhora"."sys_role_menu"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_role_menu"."role_id" IS '角色ID';

COMMENT ON COLUMN "zhora"."sys_role_menu"."menu_id" IS '菜单ID';

COMMENT ON TABLE "zhora"."sys_role_menu" IS '角色和菜单关联表';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu values ('2', '1');
insert into sys_role_menu values ('2', '2');
insert into sys_role_menu values ('2', '3');
insert into sys_role_menu values ('2', '4');
insert into sys_role_menu values ('2', '100');
insert into sys_role_menu values ('2', '101');
insert into sys_role_menu values ('2', '102');
insert into sys_role_menu values ('2', '103');
insert into sys_role_menu values ('2', '104');
insert into sys_role_menu values ('2', '105');
insert into sys_role_menu values ('2', '106');
insert into sys_role_menu values ('2', '107');
insert into sys_role_menu values ('2', '108');
insert into sys_role_menu values ('2', '109');
insert into sys_role_menu values ('2', '110');
insert into sys_role_menu values ('2', '111');
insert into sys_role_menu values ('2', '112');
insert into sys_role_menu values ('2', '113');
insert into sys_role_menu values ('2', '114');
insert into sys_role_menu values ('2', '115');
insert into sys_role_menu values ('2', '116');
insert into sys_role_menu values ('2', '500');
insert into sys_role_menu values ('2', '501');
insert into sys_role_menu values ('2', '1000');
insert into sys_role_menu values ('2', '1001');
insert into sys_role_menu values ('2', '1002');
insert into sys_role_menu values ('2', '1003');
insert into sys_role_menu values ('2', '1004');
insert into sys_role_menu values ('2', '1005');
insert into sys_role_menu values ('2', '1006');
insert into sys_role_menu values ('2', '1007');
insert into sys_role_menu values ('2', '1008');
insert into sys_role_menu values ('2', '1009');
insert into sys_role_menu values ('2', '1010');
insert into sys_role_menu values ('2', '1011');
insert into sys_role_menu values ('2', '1012');
insert into sys_role_menu values ('2', '1013');
insert into sys_role_menu values ('2', '1014');
insert into sys_role_menu values ('2', '1015');
insert into sys_role_menu values ('2', '1016');
insert into sys_role_menu values ('2', '1017');
insert into sys_role_menu values ('2', '1018');
insert into sys_role_menu values ('2', '1019');
insert into sys_role_menu values ('2', '1020');
insert into sys_role_menu values ('2', '1021');
insert into sys_role_menu values ('2', '1022');
insert into sys_role_menu values ('2', '1023');
insert into sys_role_menu values ('2', '1024');
insert into sys_role_menu values ('2', '1025');
insert into sys_role_menu values ('2', '1026');
insert into sys_role_menu values ('2', '1027');
insert into sys_role_menu values ('2', '1028');
insert into sys_role_menu values ('2', '1029');
insert into sys_role_menu values ('2', '1030');
insert into sys_role_menu values ('2', '1031');
insert into sys_role_menu values ('2', '1032');
insert into sys_role_menu values ('2', '1033');
insert into sys_role_menu values ('2', '1034');
insert into sys_role_menu values ('2', '1035');
insert into sys_role_menu values ('2', '1036');
insert into sys_role_menu values ('2', '1037');
insert into sys_role_menu values ('2', '1038');
insert into sys_role_menu values ('2', '1039');
insert into sys_role_menu values ('2', '1040');
insert into sys_role_menu values ('2', '1041');
insert into sys_role_menu values ('2', '1042');
insert into sys_role_menu values ('2', '1043');
insert into sys_role_menu values ('2', '1044');
insert into sys_role_menu values ('2', '1045');
insert into sys_role_menu values ('2', '1046');
insert into sys_role_menu values ('2', '1047');
insert into sys_role_menu values ('2', '1048');
insert into sys_role_menu values ('2', '1049');
insert into sys_role_menu values ('2', '1050');
insert into sys_role_menu values ('2', '1051');
insert into sys_role_menu values ('2', '1052');
insert into sys_role_menu values ('2', '1053');
insert into sys_role_menu values ('2', '1054');
insert into sys_role_menu values ('2', '1055');
insert into sys_role_menu values ('2', '1056');
insert into sys_role_menu values ('2', '1057');
insert into sys_role_menu values ('2', '1058');
insert into sys_role_menu values ('2', '1059');
insert into sys_role_menu values ('2', '1060');
insert into sys_role_menu values ('2', '1061');

-- ----------------------------
-- 8、角色和部门关联表  角色1-N部门
-- ----------------------------
CREATE TABLE "zhora"."sys_role_dept" (
     "role_id" int8 NOT NULL,
     "dept_id" int8 NOT NULL,
     CONSTRAINT "sys_role_dept_pkey" PRIMARY KEY ("role_id", "dept_id")
)
;

ALTER TABLE "zhora"."sys_role_dept"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_role_dept"."role_id" IS '角色ID';

COMMENT ON COLUMN "zhora"."sys_role_dept"."dept_id" IS '部门ID';

COMMENT ON TABLE "zhora"."sys_role_dept" IS '角色和部门关联表';

-- ----------------------------
-- 初始化-角色和部门关联表数据
-- ----------------------------
insert into sys_role_dept values ('2', '100');
insert into sys_role_dept values ('2', '101');
insert into sys_role_dept values ('2', '105');

-- ----------------------------
-- 9、用户与岗位关联表  用户1-N岗位
-- ----------------------------
CREATE TABLE "zhora"."sys_user_post" (
     "user_id" int8 NOT NULL,
     "post_id" int8 NOT NULL,
     CONSTRAINT "sys_user_post_pkey" PRIMARY KEY ("user_id", "post_id")
)
;

ALTER TABLE "zhora"."sys_user_post"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_user_post"."user_id" IS '用户ID';

COMMENT ON COLUMN "zhora"."sys_user_post"."post_id" IS '岗位ID';

COMMENT ON TABLE "zhora"."sys_user_post" IS '用户与岗位关联表';

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------
insert into sys_user_post values ('1', '1');
insert into sys_user_post values ('2', '2');


-- ----------------------------
-- 10、操作日志记录
-- ----------------------------
CREATE TABLE "zhora"."sys_oper_log" (
    "oper_id" int8 NOT NULL,
    "title" varchar(50) COLLATE "pg_catalog"."default",
    "business_type" int2 DEFAULT 0,
    "method" varchar(200) COLLATE "pg_catalog"."default",
    "request_method" varchar(10) COLLATE "pg_catalog"."default",
    "operator_type" int2 DEFAULT 0,
    "oper_name" varchar(50) COLLATE "pg_catalog"."default",
    "dept_name" varchar(50) COLLATE "pg_catalog"."default",
    "oper_url" varchar(255) COLLATE "pg_catalog"."default",
    "oper_ip" varchar(128) COLLATE "pg_catalog"."default",
    "oper_location" varchar(255) COLLATE "pg_catalog"."default",
    "oper_param" varchar(2000) COLLATE "pg_catalog"."default",
    "json_result" varchar(2000) COLLATE "pg_catalog"."default",
    "status" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
    "error_msg" varchar(2000) COLLATE "pg_catalog"."default",
    "oper_time" timestamp(6),
    "cost_time" int8,
    CONSTRAINT "sys_oper_log_pkey" PRIMARY KEY ("oper_id"),
    CONSTRAINT "idx_sys_oper_log_bt" UNIQUE ("business_type"),
    CONSTRAINT "idx_sys_oper_log_s" UNIQUE ("status"),
    CONSTRAINT "idx_sys_oper_log_ot" UNIQUE ("oper_time")
)
;

ALTER TABLE "zhora"."sys_oper_log"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_oper_log"."oper_id" IS '日志主键';

COMMENT ON COLUMN "zhora"."sys_oper_log"."title" IS '模块标题';

COMMENT ON COLUMN "zhora"."sys_oper_log"."business_type" IS '业务类型（0其它 1新增 2修改 3删除）';

COMMENT ON COLUMN "zhora"."sys_oper_log"."method" IS '方法名称';

COMMENT ON COLUMN "zhora"."sys_oper_log"."request_method" IS '请求方式';

COMMENT ON COLUMN "zhora"."sys_oper_log"."operator_type" IS '操作类别（0其它 1后台用户 2手机端用户）';

COMMENT ON COLUMN "zhora"."sys_oper_log"."oper_name" IS '操作人员';

COMMENT ON COLUMN "zhora"."sys_oper_log"."dept_name" IS '部门名称';

COMMENT ON COLUMN "zhora"."sys_oper_log"."oper_url" IS '请求URL';

COMMENT ON COLUMN "zhora"."sys_oper_log"."oper_ip" IS '主机地址';

COMMENT ON COLUMN "zhora"."sys_oper_log"."oper_location" IS '操作地点';

COMMENT ON COLUMN "zhora"."sys_oper_log"."oper_param" IS '请求参数';

COMMENT ON COLUMN "zhora"."sys_oper_log"."json_result" IS '返回参数';

COMMENT ON COLUMN "zhora"."sys_oper_log"."status" IS '操作状态（0正常 1异常）';

COMMENT ON COLUMN "zhora"."sys_oper_log"."error_msg" IS '错误消息';

COMMENT ON COLUMN "zhora"."sys_oper_log"."oper_time" IS '操作时间';

COMMENT ON COLUMN "zhora"."sys_oper_log"."cost_time" IS '消耗时间';

COMMENT ON TABLE "zhora"."sys_oper_log" IS '操作日志记录';


-- ----------------------------
-- 11、字典类型表
-- ----------------------------
CREATE TABLE "zhora"."sys_dict_type" (
     "dict_id" int8 NOT NULL,
     "dict_name" varchar(100) COLLATE "pg_catalog"."default",
     "dict_type" varchar(100) COLLATE "pg_catalog"."default",
     "status" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
     "remark" varchar(500) COLLATE "pg_catalog"."default",
     CONSTRAINT "sys_dict_type_pkey" PRIMARY KEY ("dict_id")
)
;

ALTER TABLE "zhora"."sys_dict_type"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_dict_type"."dict_id" IS '字典主键';

COMMENT ON COLUMN "zhora"."sys_dict_type"."dict_name" IS '字典名称';

COMMENT ON COLUMN "zhora"."sys_dict_type"."dict_type" IS '字典类型';

COMMENT ON COLUMN "zhora"."sys_dict_type"."status" IS '状态（0正常 1停用）';

COMMENT ON COLUMN "zhora"."sys_dict_type"."remark" IS '备注';

COMMENT ON TABLE "zhora"."sys_dict_type" IS '字典类型表';

insert into sys_dict_type values(1,  '用户性别', 'sys_user_sex',        '0', '用户性别列表','0','admin', clock_timestamp(), '', null);
insert into sys_dict_type values(2,  '菜单状态', 'sys_show_hide',       '0', '菜单状态列表','0','admin', clock_timestamp(), '', null);
insert into sys_dict_type values(3,  '系统开关', 'sys_normal_disable',  '0', '系统开关列表','0','admin', clock_timestamp(), '', null);
insert into sys_dict_type values(4,  '任务状态', 'sys_job_status',      '0', '任务状态列表','0','admin', clock_timestamp(), '', null);
insert into sys_dict_type values(5,  '任务分组', 'sys_job_group',       '0', '任务分组列表','0','admin', clock_timestamp(), '', null);
insert into sys_dict_type values(6,  '系统是否', 'sys_yes_no',          '0', '系统是否列表','0','admin', clock_timestamp(), '', null);
insert into sys_dict_type values(7,  '通知类型', 'sys_notice_type',     '0', '通知类型列表','0','admin', clock_timestamp(), '', null);
insert into sys_dict_type values(8,  '通知状态', 'sys_notice_status',   '0', '通知状态列表','0','admin', clock_timestamp(), '', null);
insert into sys_dict_type values(9,  '操作类型', 'sys_oper_type',       '0', '操作类型列表','0','admin', clock_timestamp(), '', null);
insert into sys_dict_type values(10, '系统状态', 'sys_common_status',   '0', '登录状态列表','0','admin', clock_timestamp(), '', null);

-- ----------------------------
-- 12、字典数据表
-- ----------------------------
CREATE TABLE "zhora"."sys_dict_data" (
     "dict_code" int8,
     "dict_sort" int2 DEFAULT 0,
     "dict_label" varchar(100) COLLATE "pg_catalog"."default",
     "dict_value" varchar(100) COLLATE "pg_catalog"."default",
     "dict_type" varchar(100) COLLATE "pg_catalog"."default",
     "css_class" varchar(100) COLLATE "pg_catalog"."default",
     "list_class" varchar(100) COLLATE "pg_catalog"."default",
     "is_default" char(1) COLLATE "pg_catalog"."default" DEFAULT 'N'::bpchar,
     "status" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
     "remark" varchar(500) COLLATE "pg_catalog"."default",
     "del_flag" varchar(2) COLLATE "pg_catalog"."default" DEFAULT '0'::character varying,
     "create_by" varchar(64) COLLATE "pg_catalog"."default",
     "create_time" timestamp(6),
     "update_by" varchar(64) COLLATE "pg_catalog"."default",
     "update_time" timestamp(6)
)
;

ALTER TABLE "zhora"."sys_dict_data"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_dict_data"."dict_code" IS '字典编码';

COMMENT ON COLUMN "zhora"."sys_dict_data"."dict_sort" IS '字典排序';

COMMENT ON COLUMN "zhora"."sys_dict_data"."dict_label" IS '字典标签';

COMMENT ON COLUMN "zhora"."sys_dict_data"."dict_value" IS '字典键值';

COMMENT ON COLUMN "zhora"."sys_dict_data"."dict_type" IS '字典类型';

COMMENT ON COLUMN "zhora"."sys_dict_data"."css_class" IS '样式属性（其他样式扩展）';

COMMENT ON COLUMN "zhora"."sys_dict_data"."list_class" IS '表格回显样式';

COMMENT ON COLUMN "zhora"."sys_dict_data"."is_default" IS '是否默认（Y是 N否）';

COMMENT ON COLUMN "zhora"."sys_dict_data"."status" IS '状态（0正常 1停用）';

COMMENT ON COLUMN "zhora"."sys_dict_data"."remark" IS '备注';

COMMENT ON COLUMN "zhora"."sys_dict_data"."del_flag" IS '删除标记,1:已删除,0:正常';

COMMENT ON COLUMN "zhora"."sys_dict_data"."create_by" IS '创建人';

COMMENT ON COLUMN "zhora"."sys_dict_data"."create_time" IS '创建时间';

COMMENT ON COLUMN "zhora"."sys_dict_data"."update_by" IS '更新人';

COMMENT ON COLUMN "zhora"."sys_dict_data"."update_time" IS '更新时间';

COMMENT ON TABLE "zhora"."sys_dict_data" IS '字典数据表';


insert into sys_dict_data values(1,  1,  '男',       '0',       'sys_user_sex',        '',   '',        'Y', '0', '性别男','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(2,  2,  '女',       '1',       'sys_user_sex',        '',   '',        'N', '0', '性别女','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(3,  3,  '未知',     '2',       'sys_user_sex',        '',   '',        'N', '0', '性别未知','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(4,  1,  '显示',     '0',       'sys_show_hide',       '',   'primary', 'Y', '0', '显示菜单','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(5,  2,  '隐藏',     '1',       'sys_show_hide',       '',   'danger',  'N', '0', '隐藏菜单','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(6,  1,  '正常',     '0',       'sys_normal_disable',  '',   'primary', 'Y', '0', '正常状态','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(7,  2,  '停用',     '1',       'sys_normal_disable',  '',   'danger',  'N', '0', '停用状态','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(8,  1,  '正常',     '0',       'sys_job_status',      '',   'primary', 'Y', '0', '正常状态','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(9,  2,  '暂停',     '1',       'sys_job_status',      '',   'danger',  'N', '0', '停用状态','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(10, 1,  '默认',     'DEFAULT', 'sys_job_group',       '',   '',        'Y', '0', '默认分组','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(11, 2,  '系统',     'SYSTEM',  'sys_job_group',       '',   '',        'N', '0', '系统分组','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(12, 1,  '是',       'Y',       'sys_yes_no',          '',   'primary', 'Y', '0', '系统默认是','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(13, 2,  '否',       'N',       'sys_yes_no',          '',   'danger',  'N', '0', '系统默认否','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(14, 1,  '通知',     '1',       'sys_notice_type',     '',   'warning', 'Y', '0', '通知','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(15, 2,  '公告',     '2',       'sys_notice_type',     '',   'success', 'N', '0', '公告','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(16, 1,  '正常',     '0',       'sys_notice_status',   '',   'primary', 'Y', '0', '正常状态','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(17, 2,  '关闭',     '1',       'sys_notice_status',   '',   'danger',  'N', '0', '关闭状态','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(18, 99, '其他',     '0',       'sys_oper_type',       '',   'info',    'N', '0', '其他操作','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(19, 1,  '新增',     '1',       'sys_oper_type',       '',   'info',    'N', '0', '新增操作','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(20, 2,  '修改',     '2',       'sys_oper_type',       '',   'info',    'N', '0', '修改操作','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(21, 3,  '删除',     '3',       'sys_oper_type',       '',   'danger',  'N', '0', '删除操作','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(22, 4,  '授权',     '4',       'sys_oper_type',       '',   'primary', 'N', '0', '授权操作','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(23, 5,  '导出',     '5',       'sys_oper_type',       '',   'warning', 'N', '0', '导出操作','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(24, 6,  '导入',     '6',       'sys_oper_type',       '',   'warning', 'N', '0', '导入操作','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(25, 7,  '强退',     '7',       'sys_oper_type',       '',   'danger',  'N', '0', '强退操作','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(26, 8,  '生成代码', '8',       'sys_oper_type',       '',   'warning', 'N', '0', '生成操作','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(27, 9,  '清空数据', '9',       'sys_oper_type',       '',   'danger',  'N', '0', '清空操作','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(28, 1,  '成功',     '0',       'sys_common_status',   '',   'primary', 'N', '0', '正常状态','0','admin', clock_timestamp(), '', null);
insert into sys_dict_data values(29, 2,  '失败',     '1',       'sys_common_status',   '',   'danger',  'N', '0', '停用状态','0','admin', clock_timestamp(), '', null);

-- ----------------------------
-- 13、参数配置表
-- ----------------------------
CREATE TABLE "zhora"."sys_config" (
  "config_id" int4 NOT NULL DEFAULT nextval('sys_config_config_id_seq'::regclass),
  "config_name" varchar(100) COLLATE "pg_catalog"."default",
  "config_key" varchar(100) COLLATE "pg_catalog"."default",
  "config_value" varchar(500) COLLATE "pg_catalog"."default",
  "config_type" char(1) COLLATE "pg_catalog"."default" DEFAULT 'N'::bpchar,
  "remark" varchar(500) COLLATE "pg_catalog"."default",
  "del_flag" bool DEFAULT false,
  "create_by" varchar(64) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(64) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  CONSTRAINT "sys_config_pkey" PRIMARY KEY ("config_id")
)
;

ALTER TABLE "zhora"."sys_config"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_config"."config_id" IS '参数主键';

COMMENT ON COLUMN "zhora"."sys_config"."config_name" IS '参数名称';

COMMENT ON COLUMN "zhora"."sys_config"."config_key" IS '参数键名';

COMMENT ON COLUMN "zhora"."sys_config"."config_value" IS '参数键值';

COMMENT ON COLUMN "zhora"."sys_config"."config_type" IS '系统内置（Y是 N否）';

COMMENT ON COLUMN "zhora"."sys_config"."remark" IS '备注';

COMMENT ON COLUMN "zhora"."sys_config"."del_flag" IS '删除标记,true:已删除,false:正常';

COMMENT ON COLUMN "zhora"."sys_config"."create_by" IS '创建人';

COMMENT ON COLUMN "zhora"."sys_config"."create_time" IS '创建时间';

COMMENT ON COLUMN "zhora"."sys_config"."update_by" IS '更新人';

COMMENT ON COLUMN "zhora"."sys_config"."update_time" IS '更新时间';

COMMENT ON TABLE "zhora"."sys_config" IS '参数配置表';

INSERT INTO "zhora"."sys_config" ("config_id", "config_name", "config_key", "config_value", "config_type", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow', FALSE, 'admin', '2025-08-19 09:40:08.056673', '', NULL);
INSERT INTO "zhora"."sys_config" ("config_id", "config_name", "config_key", "config_value", "config_type", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', '初始化密码 123456', FALSE, 'admin', '2025-08-19 09:40:08.058604', '', NULL);
INSERT INTO "zhora"."sys_config" ("config_id", "config_name", "config_key", "config_value", "config_type", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue', FALSE, 'admin', '2025-08-19 09:40:08.060143', '', NULL);
INSERT INTO "zhora"."sys_config" ("config_id", "config_name", "config_key", "config_value", "config_type", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (4, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', '是否开启注册用户功能（true开启，false关闭）', FALSE, 'admin', '2025-08-19 09:40:08.061814', '', NULL);
INSERT INTO "zhora"."sys_config" ("config_id", "config_name", "config_key", "config_value", "config_type", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (5, '用户管理-密码字符范围', 'sys.account.chrtype', '0', 'Y', '默认任意字符范围，0任意（密码可以输入任意字符），1数字（密码只能为0-9数字），2英文字母（密码只能为a-z和A-Z字母），3字母和数字（密码必须包含字母，数字）,4字母数字和特殊字符（目前支持的特殊字符包括：~!@#$%^&*()-=_+）', FALSE, 'admin', '2025-08-19 09:40:08.063523', '', NULL);
INSERT INTO "zhora"."sys_config" ("config_id", "config_name", "config_key", "config_value", "config_type", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (6, '用户管理-初始密码修改策略', 'sys.account.initPasswordModify', '1', 'Y', '0：初始密码修改策略关闭，没有任何提示，1：提醒用户，如果未修改初始密码，则在登录时就会提醒修改密码对话框', FALSE, 'admin', '2025-08-19 09:40:08.064718', '', NULL);
INSERT INTO "zhora"."sys_config" ("config_id", "config_name", "config_key", "config_value", "config_type", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (7, '用户管理-账号密码更新周期', 'sys.account.passwordValidateDays', '0', 'Y', '密码更新周期（填写数字，数据初始化值为0不限制，若修改必须为大于0小于365的正整数），如果超过这个周期登录系统时，则在登录时就会提醒修改密码对话框', FALSE, 'admin', '2025-08-19 09:40:08.065821', '', NULL);
INSERT INTO "zhora"."sys_config" ("config_id", "config_name", "config_key", "config_value", "config_type", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (8, '主框架页-菜单导航显示风格', 'sys.index.menuStyle', 'default', 'Y', '菜单导航显示风格（default为左侧导航菜单，topnav为顶部导航菜单）', FALSE, 'admin', '2025-08-19 09:40:08.066894', '', NULL);
INSERT INTO "zhora"."sys_config" ("config_id", "config_name", "config_key", "config_value", "config_type", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (9, '主框架页-是否开启页脚', 'sys.index.footer', 'true', 'Y', '是否开启底部页脚显示（true显示，false隐藏）', FALSE, 'admin', '2025-08-19 09:40:08.06801', '', NULL);
INSERT INTO "zhora"."sys_config" ("config_id", "config_name", "config_key", "config_value", "config_type", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (10, '主框架页-是否开启页签', 'sys.index.tagsView', 'true', 'Y', '是否开启菜单多页签显示（true显示，false隐藏）', FALSE, 'admin', '2025-08-19 09:40:08.069405', '', NULL);
INSERT INTO "zhora"."sys_config" ("config_id", "config_name", "config_key", "config_value", "config_type", "remark", "del_flag", "create_by", "create_time", "update_by", "update_time") VALUES (11, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）', FALSE, 'admin', '2025-08-19 09:40:08.070837', '', NULL);

-- ----------------------------
-- 14、系统访问记录
-- ----------------------------
CREATE TABLE "zhora"."sys_logininfor" (
  "info_id" int4 NOT NULL DEFAULT nextval('sys_logininfor_info_id_seq'::regclass),
  "login_name" varchar(50) COLLATE "pg_catalog"."default",
  "ipaddr" varchar(128) COLLATE "pg_catalog"."default",
  "login_location" varchar(255) COLLATE "pg_catalog"."default",
  "browser" varchar(50) COLLATE "pg_catalog"."default",
  "os" varchar(50) COLLATE "pg_catalog"."default",
  "status" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
  "msg" varchar(255) COLLATE "pg_catalog"."default",
  "login_time" timestamp(6),
  CONSTRAINT "sys_logininfor_pkey" PRIMARY KEY ("info_id"),
  CONSTRAINT "idx_sys_logininfor_s" UNIQUE ("status"),
  CONSTRAINT "idx_sys_logininfor_lt" UNIQUE ("login_time")
)
;

ALTER TABLE "zhora"."sys_logininfor"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_logininfor"."info_id" IS '访问ID';

COMMENT ON COLUMN "zhora"."sys_logininfor"."login_name" IS '登录账号';

COMMENT ON COLUMN "zhora"."sys_logininfor"."ipaddr" IS '登录IP地址';

COMMENT ON COLUMN "zhora"."sys_logininfor"."login_location" IS '登录地点';

COMMENT ON COLUMN "zhora"."sys_logininfor"."browser" IS '浏览器类型';

COMMENT ON COLUMN "zhora"."sys_logininfor"."os" IS '操作系统';

COMMENT ON COLUMN "zhora"."sys_logininfor"."status" IS '登录状态（0成功 1失败）';

COMMENT ON COLUMN "zhora"."sys_logininfor"."msg" IS '提示消息';

COMMENT ON COLUMN "zhora"."sys_logininfor"."login_time" IS '访问时间';

COMMENT ON TABLE "zhora"."sys_logininfor" IS '系统访问记录';

-- ----------------------------
-- 15、在线用户记录
-- ----------------------------
CREATE TABLE "zhora"."sys_user_online" (
   "sessionId" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
   "login_name" varchar(50) COLLATE "pg_catalog"."default",
   "dept_name" varchar(50) COLLATE "pg_catalog"."default",
   "ipaddr" varchar(128) COLLATE "pg_catalog"."default",
   "login_location" varchar(255) COLLATE "pg_catalog"."default",
   "browser" varchar(50) COLLATE "pg_catalog"."default",
   "os" varchar(50) COLLATE "pg_catalog"."default",
   "status" varchar(10) COLLATE "pg_catalog"."default",
   "start_timestamp" timestamp(6),
   "last_access_time" timestamp(6),
   "expire_time" int2,
   CONSTRAINT "sys_user_online_pkey" PRIMARY KEY ("sessionId")
)
;

ALTER TABLE "zhora"."sys_user_online"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_user_online"."sessionId" IS '用户会话id';

COMMENT ON COLUMN "zhora"."sys_user_online"."login_name" IS '登录账号';

COMMENT ON COLUMN "zhora"."sys_user_online"."dept_name" IS '部门名称';

COMMENT ON COLUMN "zhora"."sys_user_online"."ipaddr" IS '登录IP地址';

COMMENT ON COLUMN "zhora"."sys_user_online"."login_location" IS '登录地点';

COMMENT ON COLUMN "zhora"."sys_user_online"."browser" IS '浏览器类型';

COMMENT ON COLUMN "zhora"."sys_user_online"."os" IS '操作系统';

COMMENT ON COLUMN "zhora"."sys_user_online"."status" IS '在线状态on_line在线off_line离线';

COMMENT ON COLUMN "zhora"."sys_user_online"."start_timestamp" IS 'session创建时间';

COMMENT ON COLUMN "zhora"."sys_user_online"."last_access_time" IS 'session最后访问时间';

COMMENT ON COLUMN "zhora"."sys_user_online"."expire_time" IS '超时时间，单位为分钟';

COMMENT ON TABLE "zhora"."sys_user_online" IS '在线用户记录';


-- ----------------------------
-- 16、定时任务调度表
-- ----------------------------
CREATE TABLE "zhora"."sys_job" (
   "job_id" int4 NOT NULL DEFAULT nextval('sys_job_job_id_seq'::regclass),
   "job_name" varchar(64) COLLATE "pg_catalog"."default",
   "job_group" varchar(64) COLLATE "pg_catalog"."default",
   "invoke_target" varchar(500) COLLATE "pg_catalog"."default",
   "cron_expression" varchar(255) COLLATE "pg_catalog"."default",
   "misfire_policy" varchar(20) COLLATE "pg_catalog"."default" DEFAULT 3,
   "concurrent" char(1) COLLATE "pg_catalog"."default" DEFAULT 1,
   "status" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
   "remark" varchar(500) COLLATE "pg_catalog"."default",
   "del_flag" varchar(2) COLLATE "pg_catalog"."default" DEFAULT '0'::character varying,
   "create_by" varchar(64) COLLATE "pg_catalog"."default",
   "create_time" timestamp(6),
   "update_by" varchar(64) COLLATE "pg_catalog"."default",
   "update_time" timestamp(6),
   CONSTRAINT "sys_job_pkey" PRIMARY KEY ("job_id")
)
;

ALTER TABLE "zhora"."sys_job"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_job"."job_id" IS '任务ID';

COMMENT ON COLUMN "zhora"."sys_job"."job_name" IS '任务名称';

COMMENT ON COLUMN "zhora"."sys_job"."job_group" IS '任务组名';

COMMENT ON COLUMN "zhora"."sys_job"."invoke_target" IS '调用目标字符串';

COMMENT ON COLUMN "zhora"."sys_job"."cron_expression" IS 'cron执行表达式';

COMMENT ON COLUMN "zhora"."sys_job"."misfire_policy" IS '计划执行错误策略（1立即执行 2执行一次 3放弃执行）';

COMMENT ON COLUMN "zhora"."sys_job"."concurrent" IS '是否并发执行（0允许 1禁止）';

COMMENT ON COLUMN "zhora"."sys_job"."status" IS '状态（0正常 1暂停）';

COMMENT ON COLUMN "zhora"."sys_job"."remark" IS '备注信息';

COMMENT ON COLUMN "zhora"."sys_job"."del_flag" IS '删除标记,1:已删除,0:正常';

COMMENT ON COLUMN "zhora"."sys_job"."create_by" IS '创建人';

COMMENT ON COLUMN "zhora"."sys_job"."create_time" IS '创建时间';

COMMENT ON COLUMN "zhora"."sys_job"."update_by" IS '更新人';

COMMENT ON COLUMN "zhora"."sys_job"."update_time" IS '更新时间';

COMMENT ON TABLE "zhora"."sys_job" IS '定时任务调度表';

insert into sys_job values(1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams',        '0/10 * * * * ?', '3', '1', '1', '','0','admin', clock_timestamp(), '', null);
insert into sys_job values(2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\"ry\")',  '0/15 * * * * ?', '3', '1', '1', '','0','admin', clock_timestamp(), '', null);
insert into sys_job values(3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\"ry\", true, 2000L, 316.50D, 100)',  '0/20 * * * * ?', '3', '1', '1', '','0','admin', clock_timestamp(), '', null);


-- ----------------------------
-- 17、定时任务调度日志表
-- ----------------------------
CREATE TABLE "zhora"."sys_job_log" (
   "job_log_id" int4 NOT NULL DEFAULT nextval('sys_job_log_job_log_id_seq'::regclass),
   "job_name" varchar(64) COLLATE "pg_catalog"."default",
   "job_group" varchar(64) COLLATE "pg_catalog"."default",
   "invoke_target" varchar(500) COLLATE "pg_catalog"."default",
   "job_message" varchar(500) COLLATE "pg_catalog"."default",
   "status" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
   "exception_info" varchar(2000) COLLATE "pg_catalog"."default",
   "create_time" timestamp(6),
   CONSTRAINT "sys_job_log_pkey" PRIMARY KEY ("job_log_id")
)
;

ALTER TABLE "zhora"."sys_job_log"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_job_log"."job_log_id" IS '任务日志ID';

COMMENT ON COLUMN "zhora"."sys_job_log"."job_name" IS '任务名称';

COMMENT ON COLUMN "zhora"."sys_job_log"."job_group" IS '任务组名';

COMMENT ON COLUMN "zhora"."sys_job_log"."invoke_target" IS '调用目标字符串';

COMMENT ON COLUMN "zhora"."sys_job_log"."job_message" IS '日志信息';

COMMENT ON COLUMN "zhora"."sys_job_log"."status" IS '执行状态（0正常 1失败）';

COMMENT ON COLUMN "zhora"."sys_job_log"."exception_info" IS '异常信息';

COMMENT ON COLUMN "zhora"."sys_job_log"."create_time" IS '创建时间';

COMMENT ON TABLE "zhora"."sys_job_log" IS '定时任务调度日志表';


-- ----------------------------
-- 18、通知公告表
-- ----------------------------
CREATE TABLE "zhora"."sys_notice" (
  "notice_id" int4 NOT NULL DEFAULT nextval('sys_notice_notice_id_seq'::regclass),
  "notice_title" varchar(50) COLLATE "pg_catalog"."default",
  "notice_type" char(1) COLLATE "pg_catalog"."default",
  "notice_content" bytea,
  "status" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
  "remark" varchar(500) COLLATE "pg_catalog"."default",
  "del_flag" varchar(2) COLLATE "pg_catalog"."default" DEFAULT '0'::character varying,
  "create_by" varchar(64) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(64) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  CONSTRAINT "sys_notice_pkey" PRIMARY KEY ("notice_id")
)
;

ALTER TABLE "zhora"."sys_notice"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."sys_notice"."notice_id" IS '公告ID';

COMMENT ON COLUMN "zhora"."sys_notice"."notice_title" IS '公告标题';

COMMENT ON COLUMN "zhora"."sys_notice"."notice_type" IS '公告类型（1通知 2公告）';

COMMENT ON COLUMN "zhora"."sys_notice"."notice_content" IS '公告内容';

COMMENT ON COLUMN "zhora"."sys_notice"."status" IS '公告状态（0正常 1关闭）';

COMMENT ON COLUMN "zhora"."sys_notice"."remark" IS '备注';

COMMENT ON COLUMN "zhora"."sys_notice"."del_flag" IS '删除标记,1:已删除,0:正常';

COMMENT ON COLUMN "zhora"."sys_notice"."create_by" IS '创建人';

COMMENT ON COLUMN "zhora"."sys_notice"."create_time" IS '创建时间';

COMMENT ON COLUMN "zhora"."sys_notice"."update_by" IS '更新人';

COMMENT ON COLUMN "zhora"."sys_notice"."update_time" IS '更新时间';

COMMENT ON TABLE "zhora"."sys_notice" IS '通知公告表';

-- ----------------------------
-- 初始化-公告信息表数据
-- ----------------------------
insert into sys_notice values('1', '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', '管理员','0','admin', clock_timestamp(), '', null);
insert into sys_notice values('2', '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容',   '0', '管理员','0','admin', clock_timestamp(), '', null);


-- ----------------------------
-- 19、代码生成业务表
-- ----------------------------
CREATE TABLE "zhora"."gen_table" (
     "table_id" int4 NOT NULL DEFAULT nextval('gen_table_table_id_seq'::regclass),
     "table_name" varchar(200) COLLATE "pg_catalog"."default",
     "table_comment" varchar(500) COLLATE "pg_catalog"."default",
     "sub_table_name" varchar(64) COLLATE "pg_catalog"."default",
     "sub_table_fk_name" varchar(64) COLLATE "pg_catalog"."default",
     "class_name" varchar(100) COLLATE "pg_catalog"."default",
     "tpl_category" varchar(200) COLLATE "pg_catalog"."default" DEFAULT 'crud'::character varying,
     "package_name" varchar(100) COLLATE "pg_catalog"."default",
     "module_name" varchar(30) COLLATE "pg_catalog"."default",
     "business_name" varchar(30) COLLATE "pg_catalog"."default",
     "function_name" varchar(50) COLLATE "pg_catalog"."default",
     "function_author" varchar(50) COLLATE "pg_catalog"."default",
     "form_col_num" int2 DEFAULT 1,
     "gen_type" char(1) COLLATE "pg_catalog"."default" DEFAULT 0,
     "gen_path" varchar(200) COLLATE "pg_catalog"."default" DEFAULT '/'::character varying,
     "options" varchar(1000) COLLATE "pg_catalog"."default",
     "remark" varchar(500) COLLATE "pg_catalog"."default",
     "del_flag" varchar(2) COLLATE "pg_catalog"."default" DEFAULT '0'::character varying,
     "create_by" varchar(64) COLLATE "pg_catalog"."default",
     "create_time" timestamp(6),
     "update_by" varchar(64) COLLATE "pg_catalog"."default",
     "update_time" timestamp(6),
     CONSTRAINT "gen_table_pkey" PRIMARY KEY ("table_id")
)
;

ALTER TABLE "zhora"."gen_table"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."gen_table"."table_id" IS '编号';

COMMENT ON COLUMN "zhora"."gen_table"."table_name" IS '表名称';

COMMENT ON COLUMN "zhora"."gen_table"."table_comment" IS '表描述';

COMMENT ON COLUMN "zhora"."gen_table"."sub_table_name" IS '关联子表的表名';

COMMENT ON COLUMN "zhora"."gen_table"."sub_table_fk_name" IS '子表关联的外键名';

COMMENT ON COLUMN "zhora"."gen_table"."class_name" IS '实体类名称';

COMMENT ON COLUMN "zhora"."gen_table"."tpl_category" IS '使用的模板（crud单表操作 tree树表操作 sub主子表操作）';

COMMENT ON COLUMN "zhora"."gen_table"."package_name" IS '生成包路径';

COMMENT ON COLUMN "zhora"."gen_table"."module_name" IS '生成模块名';

COMMENT ON COLUMN "zhora"."gen_table"."business_name" IS '生成业务名';

COMMENT ON COLUMN "zhora"."gen_table"."function_name" IS '生成功能名';

COMMENT ON COLUMN "zhora"."gen_table"."function_author" IS '生成功能作者';

COMMENT ON COLUMN "zhora"."gen_table"."form_col_num" IS '表单布局（单列 双列 三列）';

COMMENT ON COLUMN "zhora"."gen_table"."gen_type" IS '生成代码方式（0zip压缩包 1自定义路径）';

COMMENT ON COLUMN "zhora"."gen_table"."gen_path" IS '生成路径（不填默认项目路径）';

COMMENT ON COLUMN "zhora"."gen_table"."options" IS '其它生成选项';

COMMENT ON COLUMN "zhora"."gen_table"."remark" IS '备注';

COMMENT ON COLUMN "zhora"."gen_table"."del_flag" IS '删除标记,1:已删除,0:正常';

COMMENT ON COLUMN "zhora"."gen_table"."create_by" IS '创建人';

COMMENT ON COLUMN "zhora"."gen_table"."create_time" IS '创建时间';

COMMENT ON COLUMN "zhora"."gen_table"."update_by" IS '更新人';

COMMENT ON COLUMN "zhora"."gen_table"."update_time" IS '更新时间';

COMMENT ON TABLE "zhora"."gen_table" IS '代码生成业务表';


-- ----------------------------
-- 20、代码生成业务表字段
-- ----------------------------
CREATE TABLE "zhora"."gen_table_column" (
    "column_id" int4 NOT NULL DEFAULT nextval('gen_table_column_column_id_seq'::regclass),
    "table_id" int8 NOT NULL,
    "column_name" varchar(200) COLLATE "pg_catalog"."default",
    "column_comment" varchar(500) COLLATE "pg_catalog"."default",
    "column_type" varchar(100) COLLATE "pg_catalog"."default",
    "java_type" varchar(500) COLLATE "pg_catalog"."default",
    "java_field" varchar(200) COLLATE "pg_catalog"."default",
    "is_pk" char(1) COLLATE "pg_catalog"."default",
    "is_increment" char(1) COLLATE "pg_catalog"."default",
    "is_required" char(1) COLLATE "pg_catalog"."default",
    "is_insert" char(1) COLLATE "pg_catalog"."default",
    "is_edit" char(1) COLLATE "pg_catalog"."default",
    "is_list" char(1) COLLATE "pg_catalog"."default",
    "is_query" char(1) COLLATE "pg_catalog"."default",
    "query_type" varchar(200) COLLATE "pg_catalog"."default" DEFAULT 'EQ'::character varying,
    "html_type" varchar(200) COLLATE "pg_catalog"."default",
    "dict_type" varchar(200) COLLATE "pg_catalog"."default",
    "sort" int2,
    "del_flag" varchar(2) COLLATE "pg_catalog"."default" DEFAULT '0'::character varying,
    "create_by" varchar(64) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by" varchar(64) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    CONSTRAINT "gen_table_column_pkey" PRIMARY KEY ("column_id")
)
;

ALTER TABLE "zhora"."gen_table_column"
    OWNER TO "zhora";

COMMENT ON COLUMN "zhora"."gen_table_column"."column_id" IS '编号';

COMMENT ON COLUMN "zhora"."gen_table_column"."table_id" IS '归属表编号';

COMMENT ON COLUMN "zhora"."gen_table_column"."column_name" IS '列名称';

COMMENT ON COLUMN "zhora"."gen_table_column"."column_comment" IS '列描述';

COMMENT ON COLUMN "zhora"."gen_table_column"."column_type" IS '列类型';

COMMENT ON COLUMN "zhora"."gen_table_column"."java_type" IS 'JAVA类型';

COMMENT ON COLUMN "zhora"."gen_table_column"."java_field" IS 'JAVA字段名';

COMMENT ON COLUMN "zhora"."gen_table_column"."is_pk" IS '是否主键（1是）';

COMMENT ON COLUMN "zhora"."gen_table_column"."is_increment" IS '是否自增（1是）';

COMMENT ON COLUMN "zhora"."gen_table_column"."is_required" IS '是否必填（1是）';

COMMENT ON COLUMN "zhora"."gen_table_column"."is_insert" IS '是否为插入字段（1是）';

COMMENT ON COLUMN "zhora"."gen_table_column"."is_edit" IS '是否编辑字段（1是）';

COMMENT ON COLUMN "zhora"."gen_table_column"."is_list" IS '是否列表字段（1是）';

COMMENT ON COLUMN "zhora"."gen_table_column"."is_query" IS '是否查询字段（1是）';

COMMENT ON COLUMN "zhora"."gen_table_column"."query_type" IS '查询方式（等于、不等于、大于、小于、范围）';

COMMENT ON COLUMN "zhora"."gen_table_column"."html_type" IS '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）';

COMMENT ON COLUMN "zhora"."gen_table_column"."dict_type" IS '字典类型';

COMMENT ON COLUMN "zhora"."gen_table_column"."sort" IS '排序';

COMMENT ON COLUMN "zhora"."gen_table_column"."del_flag" IS '删除标记,1:已删除,0:正常';

COMMENT ON COLUMN "zhora"."gen_table_column"."create_by" IS '创建人';

COMMENT ON COLUMN "zhora"."gen_table_column"."create_time" IS '创建时间';

COMMENT ON COLUMN "zhora"."gen_table_column"."update_by" IS '更新人';

COMMENT ON COLUMN "zhora"."gen_table_column"."update_time" IS '更新时间';

COMMENT ON TABLE "zhora"."gen_table_column" IS '代码生成业务表字段';
