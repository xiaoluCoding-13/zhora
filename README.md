# zhora-agent 项目简介

## 🌐 项目概述
**zhora-agent**（`Zhehen's Heuristic Operational Response Agent`）是基于Java 21和LangChain4j框架构建的新一代智能体系统。
是一个基于 Java 的项目，用于实现聊天、文档问答 (RAG)、PPT 生成、流式响应等功能。项目包含多个模块，分别对应不同的功能和服务。

## 依赖
- Java 21+
- Spring Boot
- LangChain4j
- Redis (用于聊天记忆存储)
- Maven 作为构建工具

## 功能说明
- **聊天接口**: 提供普通聊天和流式聊天接口。
- **RAG 文档问答**: 支持上传文档并进行检索增强生成 (RAG) 问答。
- **PPT 生成**: 输入描述，由大模型生成 PPT 内容。
- **MCP 工具**: 集成 MCP 工具，如获取当前时间。
- **聊天历史记录**: 支持带有历史记录的问答，使用 Redis 存储。

## 许可证
请查看仓库根目录的 LICENSE 文件。
