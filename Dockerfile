FROM mastodonc/basejava

COPY target/whiner-slf4j.jar /root/whiner-slf4j.jar
COPY run-whiner.sh /root/run-whiner.sh

ENV PORT 3000

EXPOSE 3000
EXPOSE 5001

CMD ["/root/run-whiner.sh"]

