

# zhora 项目简介

## 🌐 项目概述
**zhora**（`Zhehen's Heuristic Operational Response Agent`）是基于 Java 21 和 LangChain4j �arsenals 构建的新一代智能体系统，支持聊天、文档问答 (RAG)、PPT 生成、流式响应等功能。

本项目采用模块化设计，便于扩展和维护，主要模块包括：
- **zhora-admin** - 提供 AI 相关接口服务和核心功能实现
- **zhora-front** - 前端服务模块
- **zhora-common** - 公共工具类和通用组件
- **zhora-service** - 数据服务与业务逻辑层
- **zhora-mapper** - 数据库访问接口
- **zhora-entity** - 数据实体模型
- **zhora-dto** - 数据传输对象定义
- **zhora-constant** - 公共常量定义
- **zhora-enums** - 枚举类型定义

## ⚙️ 功能说明
- **聊天接口**：提供标准聊天和流式响应聊天接口。
- **RAG 文档问答**：支持文档上传并基于检索增强生成进行问答。
- **PPT 生成**：通过 AI 生成 PPT 内容。
- **MCP 工具**：集成系统工具，如获取当前时间。
- **聊天历史记录**：基于 Redis 实现聊天记忆存储，支持带上下文的连续对话。

## 📦 依赖要求
- **Java 21+**
- **Spring Boot**
- **LangChain4j**
- **Redis**：用于存储聊天记录。
- **Maven**：作为项目构建工具。

## 📁 模块详情
### zhora-admin
作为 AI 聊天和工具的核心模块，包含以下组件：
- **ZhoraAdminApplication.java**：主程序入口。
- **AiController.java**：提供 AI 相关的 REST 接口。
- **ChatModelInitializer.java**：初始化 AI 模型，如 `DeepSeekChatModel` 和 `QwenChatModel`。
- **ToolsInitializer.java**：集成和初始化 MCP 工具。
- **PromptConfiguration.java**：加载和配置 AI 提示词模板。
- **InMemoryChatMemoryStore.java**：实现本地聊天记忆存储。

### zhora-front
- **ZhoraFrontApplication.java**：前端服务的主程序入口。

### zhora-common
提供通用工具类和异常处理框架，包括：
- **StringUtils, NumberUtil, MessageUtils**：字符串、数字、消息处理工具。
- **Exception 模块**：统一的异常处理体系，包含 `BaseException`, `UserException`, `ServiceException` �6540
- **XSS 安全过滤**：通过 `XssFilter` 和 `XssValidator` 提供请求内容安全校验。

### zhora-service
- **IAiLlmConfigService.java**：AI 模型配置的服务接口。
- **AiLlmConfigServiceImpl.java**：实现 LLM 配置的业务逻辑，包括优先级模型选择和查询封装。

### zhora-mapper
- **AiLlmConfigMapper.java**：MyBatis Plus 映射接口。

### zhora-entity
- **AiLlmConfigEntity.java**：数据库实体类，对应 `ai_llm_config` 表。
- **BaseEntity.java**：所有实体的基类，提供基础字段如创建时间和更新时间。

### zhora-dto
- **AiLlmConfigDTO.java**：用于对外数据传输的模型类。
- **ChatDto.java**：聊天请求的输入参数定义。

### zhora-enums
- **ChatMode.java**：定义聊天模式，如普通对话或文档检索对话。
- **LlmCodeEnum.java**：LLM 提供商编码枚举。
- **LlmTypeEnum.java**：区分 LLM 类型（如 ChatModel, EmbeddingModel）。

## 📄 许可证
本项目采用标准开源许可证，请查看项目根目录中的 LICENSE 文件以获取详细条款。

## 🚀 启动说明
1. 确保 Java 21 已安装。
2. 配置 Redis 数据库。
3. 使用 `mvn spring-boot:run` 启动 zhora-admin 模块。
4. 启动 zhora-front 模块以提供前端服务。

## 📚 文档与支持
项目文档和使用说明请参考 Gitee 项目页面或通过 Swagger UI 查看 API 文档。

## 🛠️ 构建与部署
使用 Maven 进行构建，运行 `mvn clean package` 生成部署包。根据环境配置 `application-local.yml` 或 `application-dev.yml` 文件以适配不同部署场景。

## 📬 反馈与贡献
欢迎提交 issue 和 PR。请遵循项目贡献指南，确保代码质量和风格统一。