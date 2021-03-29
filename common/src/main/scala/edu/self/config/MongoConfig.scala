package edu.self.config

case class MongoConfig(
                        proto: String,
                        username: String,
                        password: String,
                        address: String,
                      ) {
  override def toString: String = {
    val r = s"${this.proto}://${this.username}:${this.password}@${address}"
    r
  }
}
