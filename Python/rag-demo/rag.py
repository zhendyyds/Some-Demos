from langchain_core.documents import Document
from langchain_core.output_parsers import StrOutputParser
from langchain_core.runnables import RunnablePassthrough, RunnableWithMessageHistory, RunnableLambda
from file_history_store import get_history
from vector_stores import VectorStoreService
from langchain_community.embeddings import DashScopeEmbeddings
import config_data as config
from langchain_community.chat_models.tongyi import ChatTongyi
from prompts import get_qa_prompt_template
from operator import itemgetter

class RagService(object):
    def __init__(self):
        self.vector_service = VectorStoreService(embedding=DashScopeEmbeddings(model=config.embedding_model_name))

        self.prompt_template = get_qa_prompt_template()

        self.chat_model = ChatTongyi(model=config.chat_model_name)

        self.chain = self.__get_chain()

    def __get_chain(self):
        """获取最终的执行链"""
        retriever = self.vector_service.get_retriever()

        def format_document(docs: list[Document]):
            if not docs:
                return config.no_document
            return "".join(f"文档片段：{doc.page_content}\n文档元数据：{doc.metadata}" for doc in docs) + "\n\n"

        chain = (
                {
                    "input": itemgetter("input"),
                    "history": itemgetter("history"),
                    "context": itemgetter("input") | retriever | format_document
                }
                | self.prompt_template
                | self.chat_model
                | StrOutputParser()
        )

        conversation_chain = RunnableWithMessageHistory(
            chain,
            get_history,
            input_messages_key="input",
            history_messages_key="history",
        )

        return conversation_chain