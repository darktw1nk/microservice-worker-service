FROM quay.io/quarkus/ubi-quarkus-native-image:19.1.1 as nativebuilder
RUN mkdir -p /tmp/ssl-libs \
  && cp /opt/graalvm/jre/lib/security/cacerts /tmp/ssl-libs \
  && cp /opt/graalvm/jre/lib/amd64/libsunec.so /tmp/ssl-libs

FROM registry.access.redhat.com/ubi8/ubi-minimal
WORKDIR /work/
COPY target/*-runner /work/application
COPY --from=nativebuilder /tmp/ssl-libs /work
RUN chmod 775 /work
EXPOSE 8080
CMD ["./application", "-Dquarkus.http.host=0.0.0.0", "-Djavax.net.ssl.trustStore=/work/cacerts"]