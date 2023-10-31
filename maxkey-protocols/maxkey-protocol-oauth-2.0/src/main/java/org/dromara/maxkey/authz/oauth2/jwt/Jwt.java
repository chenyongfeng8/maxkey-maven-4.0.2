/*
 * Copyright 2006-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.dromara.maxkey.authz.oauth2.jwt;

import org.dromara.maxkey.authz.oauth2.jwt.crypto.sign.SignatureVerifier;

/**
 * @author Luke Taylor
 */
public interface Jwt extends BinaryFormat {
	String getClaims();

	String getEncoded();

	void verifySignature(SignatureVerifier verifier);
}
