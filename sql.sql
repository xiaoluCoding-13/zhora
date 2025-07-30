CREATE TYPE llm_code_enum AS ENUM ('DEEP_SEEK', 'Q_WEN', 'Q_WEN_EMBEDDING');
CREATE TYPE llm_type_enum AS ENUM ('LLM', 'CHAT', 'EMBEDDING');

-- ----------------------------
-- Table structure for ai_llm_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."ai_llm_config";
CREATE TABLE "public"."ai_llm_config" (
  "id" int8 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "code" "public"."llm_code_enum" NOT NULL,
  "model_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "type" "public"."llm_type_enum" NOT NULL,
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
