# path
md5_path = "./md5.text"

# history_path
history_path = "./chat_history"

# Chroma
collection_name = "rag"
model_name = "text-embedding-v4"
persist_directory = "./chroma_db"

# spliter
chunk_size = 1000
chunk_overlap = 100
separators = ["\n\n", "\n", ".", "!", "?", "。", "！", "？", " ", ""]
max_split_char_number = 1000

# metadata
operator_name = "小曹"

# get_retriever
similarity_threshold = 1

# RagService
embedding_model_name = "text-embedding-v4"
chat_model_name = "qwen3.7-max"

# sessionID
session_config = {
        "configurable": {
            "session_id": "user_001",
        }
    }

# notice
fail_notice = "[跳过]内容已经存在知识库中"
sucess_notice = "[成功]内容已经成功载入向量库"

# document
no_document = "无相关参考资料"