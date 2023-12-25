/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.manager.dictionary.XPlanCodelists;
import de.latlon.xplan.manager.synthesizer.PlanContext;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XplanFlattenProperty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.jupiter.api.Test;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_53;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class XplanFlattenPropertyTest {

	@Test
	void testEvaluateWithGenericFeature() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Baugebiet_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:flaechenteil"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals("[BP_BaugebietsTeilFlaeche_1][BP_BaugebietsTeilFlaeche_2][BP_BaugebietsTeilFlaeche_3]",
				value.toString());
	}

	@Test
	void testEvaluateWithGenericReference() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Bereich_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:inhaltBPlan"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(
				"[urn:GML_28a32870-6056-4d33-aadf-e24d9175ae8f][urn:GML_a95c7054-e1a4-42ef-ac37-2076f3641e93][urn:GML_799d579d-f550-47ca-b36a-c6c96d934822][urn:GML_c62212e7-075b-4447-ac66-ca47c70631ff][urn:GML_f71ff92a-3e8a-4434-81f0-08b4609db792][urn:GML_222ff633-4f9a-42cb-958f-1928bb82b64a][urn:GML_dacfa11a-311f-4f7d-887d-967cc7d6285a][urn:GML_6bdf9bf4-e86a-4268-a54f-711fc3d97c88][urn:GML_2d946bcc-b294-48d5-88e6-bb09181f83f4][urn:GML_4bd86e24-1706-4317-a058-6300dc6e1ab7][urn:GML_e3afd507-cb9e-4aeb-8222-6ffeb06f4e8a][urn:GML_beef514c-558a-4b63-9909-741cd92f67bd][urn:GML_23065de0-ca50-4659-a2b0-618d0c386e5b][urn:GML_fa7fabfd-a7d4-4fc8-8b9b-dab08141bc6a][urn:GML_0a7e2e3a-4df6-400b-8105-3a4e5312f634][urn:GML_e04f894d-54b2-4ed5-8390-c00a31c0dd7a][urn:GML_8370543b-09d9-4afa-8d65-e5d40b203448][urn:GML_c5e26a8a-aeac-4a0c-974d-607d860c15f7][urn:GML_cddd8565-aff7-4eb4-820f-5e2cb9609c3f][urn:GML_d8322ff0-2ea5-4701-b956-3e9a805639e4][urn:GML_ea88ebba-781d-490b-ac9c-1d15ffc60436][urn:GML_4b095056-701d-4e66-8b1c-4b0a893261f7][urn:GML_257a9c3c-b8c3-4aa0-95bd-c947f367589b][urn:GML_df5a0687-ddc1-49db-b90f-d02150179725][urn:GML_1d04c77a-f64a-4354-9c65-cb7bc9b79161][urn:GML_7900b9eb-68db-401c-83be-e063fc4d5145][urn:GML_c35a46c6-2435-4b4a-8d4d-f7f34502ea48][urn:GML_c679338d-e373-49e0-ae7c-8ad8d61995a3][urn:GML_d3d239b3-dd83-4542-9558-10afd4f68b49][urn:GML_82d7469f-6bb4-4b85-b6ae-76e8eb5f4b88][urn:GML_0ab37621-b617-4bc8-bcf1-c453e1b74463][urn:GML_44db6c8a-0f0c-47e5-a614-996e8b7ad6c9][urn:GML_1d02bbd3-b1f9-43b4-995f-a662b4e1337f][urn:GML_30c5c4d6-e5d1-4f32-8fcb-cfaee0211a2e][urn:GML_704f7ece-96af-471f-9f99-b1369d44cb03][urn:GML_a9ada70d-3cde-48ba-9ab8-9873b01f59b1][urn:GML_29e351ab-70be-4af8-9698-94d52833fb27][urn:GML_60beb52f-00d0-4287-b49c-74492ce3be1a][urn:GML_4380098d-e57e-4a24-9c59-e8460ac17a4a][urn:GML_1de83651-0ac7-43a2-a586-ba700e79d946][urn:GML_834f3a55-c93a-4c8d-8a36-4d783d6dfcd1][urn:GML_cf955fad-dbf8-4da5-ad0e-288911a40b30][urn:GML_2b77aa5c-02e8-4327-8ad0-aaec1b600860][urn:GML_5237f6e2-f9d9-425c-aab8-880e76a4a65a][urn:GML_8cdbba2a-d8a2-4af5-8dd8-4509fd69612d][urn:GML_8a18687f-2b1f-4e6f-a209-6f97946218a2][urn:GML_8ae94eef-d7e1-40d8-9256-d812b956ab76][urn:GML_4c51e921-f450-4b6d-bee5-d5f3aa56b75b][urn:GML_15af7c15-733c-4130-beeb-2ea6ba7c2485][urn:GML_6727e7d7-40e7-4f55-8d1c-b3481e9253c9][urn:GML_7b05af30-90b0-4b34-b8f3-23e1ef6387b2][urn:GML_d45c185d-0324-4f66-8d63-783966c4c34a][urn:GML_1e80e6b6-5982-4cda-a5e8-d74320b94e61][urn:GML_24a312f1-5c7c-4c77-bea5-acfa50a18180][urn:GML_6a6d23f7-c8c3-4ecc-b248-4835f2ff0860][urn:GML_14f6125a-b1df-419e-9bf4-f961f5b7c352][urn:GML_6ba1abd9-8c0b-4ef4-87f9-4a75afacbba5][urn:GML_3706a209-5b52-493e-b7df-af9600606c1f][urn:GML_3e4ddc24-fd41-475f-a8ba-27830db792d9][urn:GML_210e1ffa-e959-4581-a23b-734d365a9e3c][urn:GML_10eea201-9238-488d-ae5c-665750619e84][urn:GML_afd13300-31b9-4e9a-b46d-f9de452093b1][urn:GML_426cb8ea-c4de-4361-9512-a4a7c415faa0][urn:GML_d4a3a9df-53e2-4a40-a6cd-91ef6f2c4588][urn:GML_76c847af-45f5-4933-bb1b-546cae6389cb][urn:GML_5059c847-6fd6-4e94-84eb-0f275953d6d7][urn:GML_911727f1-2769-4e2d-9143-b957d13bb841][urn:GML_9506c481-acdf-4562-bcc2-5af2f2141316][urn:GML_14928876-d19d-4f71-b232-01020e321829][urn:GML_8c17a112-bb5f-4e8a-91d9-7848aed5807f][urn:GML_af7e4b99-0ef2-4dd2-ab60-646b1feed564][urn:GML_118b0f59-2e8f-43e0-9555-86b38ae4b0e3][urn:GML_4b63b99d-c17e-4278-a552-ff3f8a3548f1][urn:GML_930cc9f6-9664-4835-8b2a-6a6a1d084872][urn:GML_3650fd9e-7616-4e16-8af6-bc6fb3609b8a][urn:GML_829c0947-4a96-4582-b4ac-a832fecc6f59][urn:GML_5a31f3d7-24bc-4a65-8dea-2ff8a5df43c0][urn:GML_c4d8849e-6d5a-47e4-9af8-a1d72dad8b7f][urn:GML_86c45b92-dd83-43d7-97bc-d799fd118887][urn:GML_3506ba32-80c8-4ff3-b69e-786cf3264361][urn:GML_bb2e3b0f-0634-41e7-ab2a-f56a5c6b4325][urn:GML_abb39819-4e45-4289-8184-8e0305aa3f31][urn:GML_27044c32-98c4-45e9-b825-81ede8faba0a][urn:GML_05e683bc-39d6-4f5d-8e70-17389d98f2cc][urn:GML_7b913bee-556d-4cd9-9a86-60c1091ce86e][urn:GML_5c5ab1d6-c982-41da-9e99-ad5f83405245][urn:GML_50813e22-c181-4b7a-bec8-75b4088f8dc3][urn:GML_6fb2e790-e452-495e-8be7-281dca37439d][urn:GML_861efa60-bfd1-417d-af4a-ebbce12a19bf][urn:GML_73380d41-094a-4d48-aef8-124644b4464b][urn:GML_1cae7b98-fe04-4507-a3cd-190bf9eea0c3][urn:GML_af2f1fe5-0fb3-4acd-a9b3-dbf2b52872f4][urn:GML_8952adfb-2829-435c-8b67-6192e52f56f3][urn:GML_e5d13b2e-607f-4215-83c9-8dd41bfa4b9f][urn:GML_9046c229-8306-49c1-9038-d2db2ba5ce8e][urn:GML_4a5541dc-5ed8-4797-941e-3b8d3b5d1284][urn:GML_6cda61d8-2bde-4106-aa49-00f1f60556ed][urn:GML_a8753cb3-b8f2-4b2a-b1eb-869fd06502db][urn:GML_e7fda236-89a1-4169-b226-04c2969a8cb6][urn:GML_bbd07aa5-7955-4c71-addd-a6b8d81d52c2][urn:GML_fced3a94-dc7e-4f57-80db-13b59f66eb8c][urn:GML_71de174c-c728-462b-9a14-384c362b4c4e][urn:GML_29ba2ab8-c294-44cd-b7dd-fa5f738348ed][urn:GML_d3f8851b-aa4a-4ad3-ac14-fa5d74d797cd][urn:GML_74364560-95dc-469d-b5ca-d6e663ec615f][urn:GML_39b9847e-5761-4606-bcba-6a8a8da10c1b][urn:GML_d60cb953-7f3a-4529-836e-5ad383cf0c78][urn:GML_57f379dd-0114-42b5-b8a2-0857022ce18f][urn:GML_87fa3b50-a353-4465-953a-d2d113c3a81a][urn:GML_272465e9-c734-45bf-acbe-0bb8839298db][urn:GML_822d8673-9880-4284-aa9f-d56c0d96cbc9][urn:GML_99db5be2-862c-40f5-8f53-2c4e0134c7c2][urn:GML_d51983ad-e693-4dac-aa3b-65a34e025768][urn:GML_1343e922-e448-4a4d-8733-9385558f19af][urn:GML_5eaae706-b299-4c10-8a52-a8656e56b2b2][urn:GML_f8b9d25b-0676-40e3-a887-98db628af688][urn:GML_93f07848-4f6d-41d1-9096-44272b3f09bf][urn:GML_788ea3b0-7911-4b08-a694-9789089b41aa][urn:GML_02979472-c921-4b72-8a47-135eb32eede3][urn:GML_408aee6e-b038-4314-a632-0a029b45d0b9][urn:GML_1cacd802-a5b2-4186-8765-cfb860f8029c][urn:GML_946315b6-3f4b-46b0-aeef-09c5582bc0c4][urn:GML_16b3f7cf-0ee3-4937-87ce-b627dc71a21a][urn:GML_a12fe7f5-f2d4-48f4-9a38-a5eb88293a29][urn:GML_c1ca9588-ad34-45f0-a5be-46454098b444][urn:GML_55061762-d758-438c-a631-bf4f9c775dbe][urn:GML_6b5012d9-8957-47a1-88ce-d2c683b2eb0e][urn:GML_ec7a52e6-a96a-4060-9d37-cf2fcc1674fe][urn:GML_5bc549f1-9744-4c29-b4e1-e25c1dffae7b][urn:GML_427d7cf2-2de2-49b2-ad8b-e40cabf991c2][urn:GML_ddb4dbe9-54e3-4ce7-90ca-3489f8fb83f2][urn:GML_858dc02c-aff6-4275-9c44-0742a61bb6ca][urn:GML_b718107a-7708-46c7-8f07-7e1b32c93fc3][urn:GML_93b02374-9109-4f54-b81d-4401d373d8d2][urn:GML_5772ae1b-0dd6-4de6-a08d-0385c6d62c78][urn:GML_2025ff22-6283-4376-a4df-d50ad9e357a8][urn:GML_c9862c0e-c050-4546-8e33-856b7d4f75a5][urn:GML_36d213be-75f4-40b7-a1fa-0a08742d2121][urn:GML_c37e0d12-6126-4989-a097-881603f5df43][urn:GML_15de059b-2e75-4519-9714-38ad4923d3bc][urn:GML_948821ff-772a-4dc5-892b-732350523948][urn:GML_c3521759-33a4-452b-9b95-e2d934d51261][urn:GML_c8860c15-d6e4-4ac6-9c6a-f9c772bf97f2][urn:GML_37de0b37-d35f-4253-a8af-743d0252758b][urn:GML_d4fce499-6e1e-488e-935a-3108ce38a13f][urn:GML_d197661f-3563-4562-858b-e4c7685a7ece][urn:GML_6b84862c-4f38-4913-a112-70db0c2acafa][urn:GML_7c711717-640b-4eb5-b8a9-48f8aa757b5e][urn:GML_49e0469d-4bb8-4fde-8284-0a3bc3b3c26c][urn:GML_d4f88a50-e243-4011-a9a8-3e3c8ed1027b][urn:GML_a2d71190-29ef-4e28-a673-9a3d80f8b974][urn:GML_b70c8b13-07a3-460b-82d9-645f73d083e8][urn:GML_88b8aff6-8221-4537-be46-4d7f9772cf4e][urn:GML_f6f5397f-a999-469a-9c15-28487971a208][urn:GML_6bb011ad-f0d7-4ca5-a727-64496be07d21][urn:GML_6019f53f-8edf-4319-ae3a-13b10b5293b0][urn:GML_eed812b9-5c2b-44c3-86a3-da1d8c665b7a][urn:GML_5fe2cb60-7543-48e1-8a33-9966b1a61521][urn:GML_581ec17f-f32a-416e-b8a8-00f38e563819][urn:GML_4caac25e-bab9-4ab5-8797-0768bd78460b][urn:GML_d1a70ac4-fc47-43ba-8811-747b07e0aecf][urn:GML_efd10b98-cda4-4486-950c-6de033ac161a][urn:GML_038b51a6-6b09-476d-845d-802af1701115][urn:GML_14e84500-ca46-448d-8f23-5a1513f96bbb][urn:GML_ec883ff4-2edc-4cc9-bcea-e9a5c164867c][urn:GML_ebf1a4b6-ed6d-4294-8525-a6fe35329268][urn:GML_221609ea-2607-49c9-a2e3-d1cd039f0db7][urn:GML_9591bc56-45c2-46f5-8cf7-5d34cd356861][urn:GML_f9e2558f-3222-432d-a498-58a6f7743a6e][urn:GML_855d334e-6b1f-4bcd-85f3-fad683d4c2da][urn:GML_4108a394-7bfc-4567-ae1d-8164a1039b11][urn:GML_cfb0e03b-6778-4beb-9697-b487252e7585][urn:GML_602d8113-2a29-419c-95c5-fedd52f7eab9][urn:GML_4b89c4c3-b41c-4af5-831e-d85d5a471ab3][urn:GML_fe30f354-0098-4eac-81fa-2095d9cef188][urn:GML_51bcf0fb-cb99-4e77-91c3-7eb9e6f0f477][urn:GML_d10f15fa-70cc-499b-aec1-29910fb12b57][urn:GML_a735f69b-9399-475c-914f-f4b83cbdff73][urn:GML_807112d1-3be5-4e5d-959e-c98e7ab2ccd8][urn:GML_ecca0b7e-a357-4968-8a15-443fe21d4fb9][urn:GML_470904f4-3f69-465a-9ff2-01fe1d696c94][urn:GML_e39f064d-8e37-4b4e-ae7a-326e2f3a4a90][urn:GML_8f9800c2-1d78-4038-88dd-53115afdfa30][urn:GML_deccce9b-33bd-48c9-a16b-6d562fea39b7][urn:GML_3c6d3f14-59cc-448b-b55e-384c38751a91][urn:GML_3407c3f5-3758-4979-b839-090d5072441a][urn:GML_527479ae-5f43-46f0-9e83-53c5152aa6c5][urn:GML_772756e4-56a1-4e1c-97d1-7919d3fee16b][urn:GML_85e68bc4-530b-45c0-805d-2a96c7f4f0c4][urn:GML_453c5c38-35fb-4337-8d8d-bff15f778de1][urn:GML_24f81a65-faaa-48c0-a1bb-843e25bbe4b8][urn:GML_83e168c7-5519-459e-b76e-ef6e038e3cc7][urn:GML_972594c7-eda4-44be-a453-94d1fd0b667c][urn:GML_4bfacac5-d30a-44e9-aa52-8fd02fd36972][urn:GML_d3c2ca6e-4312-4f09-8a07-4fc49a872454][urn:GML_dc3cbb27-a593-47a7-a0b3-9de7898502d2][urn:GML_abfece32-731f-4014-a8de-bdfcde78968c][urn:GML_09eb4b5a-c653-4cb3-986d-cb0f2ae1f087][urn:GML_1cffacfd-895e-41b6-9366-2d737e228ef7][urn:GML_e9ec4a48-e525-4754-a37b-ce88a940aabd][urn:GML_61304ea3-0be9-46a3-bb7f-749458496843][urn:GML_1b77b180-eb47-4a0d-8e5d-dd452dcb6682][urn:GML_464fbab7-8380-409c-82a1-4519c788a7e2][urn:GML_de88b1fb-1724-4436-a2f9-a1209f9712da][urn:GML_7f7211da-306b-49de-be08-ca79546a40ee][urn:GML_87f0e07f-7cb2-4931-ae16-22d8af8e973a][urn:GML_9320fa48-5c72-4e4d-adda-516f07a65b1d][urn:GML_4067b6be-5c04-4c06-8e16-f8472973b11c][urn:GML_8310e31a-02eb-4771-abac-4c6dc33ef31d][urn:GML_c35a6066-6176-4a20-8f20-38281b869d89][urn:GML_48ffbabe-b89a-46c9-8c07-474d3762d67e][urn:GML_f20ac75c-a7fa-4d94-84cb-08f05d2c2b15][urn:GML_f4ea964f-fa90-4885-9481-1f6ef94eacd1][urn:GML_e2cded39-6ea1-4508-9e07-8ca952ee0210][urn:GML_62452e0a-5c58-4dc7-aa3c-cf29d167d08b][urn:GML_3f06b279-5e6b-422c-b44e-5a7c04000d61][urn:GML_2528c2cc-42f2-410d-ace4-3cd4472ef214][urn:GML_2b140783-bf4e-4629-845d-38e5ab4da50f][urn:GML_32abde79-a132-4700-8bf4-3410d11b0177][urn:GML_cd493c09-9439-47f2-aeb2-c4aa26f364cf][urn:GML_52f0132a-e927-4f9a-9009-1d1e155c623d][urn:GML_67a3fab1-1b82-4ff2-8658-fe8c71b036ea][urn:GML_673717b5-cb30-4fcd-b4a0-e6bf59a5cf75][urn:GML_97217a85-e29d-492a-8b62-2893eff498b8][urn:GML_d78c3383-6701-4753-8717-3eee96f045ca][urn:GML_8c9b95cc-50cb-447b-9ad5-18331e6976a7][urn:GML_2edc3856-7283-4834-932e-b427ad693744][urn:GML_f7653938-5496-4e38-ac45-4aef5b451d80][urn:GML_3de5c081-699b-442c-a4d2-989acdde06ae][urn:GML_cc2e36eb-7a45-448b-82d2-a1a088fa1e21][urn:GML_5206cffe-e4e5-4e0e-81e5-ca477bca57fd][urn:GML_d81251be-6ade-4378-8422-191f59e163a8][urn:GML_f869e0f7-302b-4e02-86f0-ce955206f154][urn:GML_bcd43ed0-4777-4458-bf60-d0c2f5bfb04b][urn:GML_9b9bb31d-8937-4c6b-81e5-8ef6c4a310f9][urn:GML_56478008-ea1c-49e0-8724-403b0f30f475][urn:GML_9b6cda1d-150c-49e0-b5b1-57d1ef4fc7fd][urn:GML_96e88df4-bf34-4f2a-9d07-35ff8ba31b81][urn:GML_d5e084d1-115c-4578-a55a-99c6509ad850][urn:GML_b97918dc-013d-4d20-a960-26df80f00a97][urn:GML_2c8d0815-73c3-479c-9fc8-1c51323d6367][urn:GML_3a1ca16e-5f02-48c8-8e4e-24e0e8c2e981][urn:GML_e831e788-2734-40ff-ab99-3cd436a42bdc][urn:GML_cfc23fc0-5800-427c-a25a-3729cebc2935][urn:GML_51e1035b-d7f7-4d6d-8bcf-1335cde72afc][urn:GML_844a206c-7ae7-4030-a883-7a621d9f2fe3][urn:GML_5ffe6cc1-d138-4c07-86c0-1305b0787beb][urn:GML_064d1b74-ffaf-4fde-a6e8-04ab0fceaa88][urn:GML_2f849f67-021b-49b5-a714-b0a8faa3b646][urn:GML_21e501eb-60a3-478a-b0dc-bf0864a2288d][urn:GML_37cb61d2-0b97-4dfc-b8a3-cc4caed9027d][urn:GML_80431679-63ab-4c7a-bd6c-0d33782f8019][urn:GML_e1487264-cd5d-4657-9ef5-135bc1d5b850][urn:GML_003383e6-e1d1-4544-b9af-18b7a82ffdda][urn:GML_fae9ced9-796f-4af3-b45c-230179185799][urn:GML_8598820d-1a2a-4a35-8233-3016d325eeab][urn:GML_48d43b5e-5546-4291-81a2-f613a0c8da40][urn:GML_773643a5-d46d-45dc-9bbd-8cfe9c15aa75][urn:GML_a288ba27-a5fe-451a-9123-cf0ee57d8b3e][urn:GML_92702123-880b-4a9b-b634-09766a9e0391][urn:GML_90e6cc86-3641-4b2a-9921-8fdfb37e4388][urn:GML_51a27218-8540-4f60-ad9e-472d4984d36b][urn:GML_3ff4aa1b-02d7-4d1b-97fe-d5b8d8d3dfbc][urn:GML_92d1b09e-1906-4886-8d4f-791bee08bb58][urn:GML_4eb965dd-5eb7-407a-8d52-1bfc90b489bd][urn:GML_98a54720-99b4-4861-9fa2-66318a068da2][urn:GML_bc068eee-921f-4946-903b-32c554e5b928][urn:GML_b4d36bcd-778c-4d36-a5ad-8ae0f5e0a95d][urn:GML_9cf09a0b-9857-44bd-a613-8b6dd9973425][urn:GML_6ecef469-21be-457c-bf1f-3106bf25382d][urn:GML_d795a696-b4a2-4f5a-a6af-1feeb213702e][urn:GML_bc6f0cbd-b13b-4f82-b8b2-4bf62f5f27c2][urn:GML_dc6677bf-87e4-4e25-a825-3b6264de88b1][urn:GML_b4b294ca-fa31-4bc0-ab1f-b7df228b0d1f][urn:GML_e42935e5-3619-4db5-a8be-8b3d4fe9e7f7][urn:GML_f0e664b4-eda7-409f-bfe2-9ec56177c80a][urn:GML_10f372ac-66ff-445c-8ba6-167157c289bb][urn:GML_19845b82-38da-448f-9c62-b6bb80c8e8db][urn:GML_92d5001b-adba-46bc-836c-b5f7fb5221c5][urn:GML_2f367be8-e426-431d-98cf-aa5ac6ea4500][urn:GML_4ef9e132-ffe4-47f1-bf93-48f515a188b8][urn:GML_5606f94d-dfcf-4041-b5e6-b4ce97ddebee][urn:GML_3b7ca95d-2c5f-48f8-8668-077a4482f2d8][urn:GML_2259ea0b-21ca-4cb1-a3d1-b437ae89f4b4][urn:GML_6fcd728c-e123-4bbb-a1ef-ecf0d8a7cb9b][urn:GML_de952ad1-9260-4559-9dc8-0e6adc4e0c5e][urn:GML_b67a8af2-b0b5-4aa1-8473-354ddd5ed1be][urn:GML_cf2930b6-38b1-46f5-a8b3-befcd2596e6d][urn:GML_9713f4e5-2776-4dce-b6e3-1f81da11a049][urn:GML_b088ef6c-b009-4af7-aec6-5ed090aaff10][urn:GML_ff1e862b-c938-4fe7-864c-2a59ce087256][urn:GML_bdf125ee-68dc-444f-a5cb-bd52e18d3649][urn:GML_743a7792-17be-4627-bee1-bc43efdc0481][urn:GML_16692627-2924-489d-b8cb-aa8742f3d2a9]",
				value.toString());
	}

	@Test
	void testEvaluateWithXpBegruendungAbschnitt() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Baugebiet_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(),
				new Xpath("xplan:refBegruendungInhalt"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(
				"[begruendung1 | Das ist Begründungsabschnitt No 1][begruendung2 | Das ist Begründungsabschnitt No 2]",
				value.toString());
	}

	@Test
	void testEvaluateWithXpExterneReferenz() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:refLegende"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(
				"[/getAttachment?featureID=BP_Plan_1&filename=B-Plan_Klingmuehl_Heideweg_Leg.pdf | Externe Referenz]",
				value.toString());
	}

	@Test
	void testEvaluateWithXpExterneReferenzPlan() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:refRechtsplan"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(
				"[/getAttachment?featureID=BP_Plan_1&filename=B-Plan_Klingmuehl_Heideweg.tif | Externe Referenz][/getAttachment?featureID=BP_Plan_1&filename=georef | Georeferenz]",
				value.toString());
	}

	@Test
	void testEvaluateWithXpGenerAttribut() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Baugebiet_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:hatGenerAttribut"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals("[\"attribut1\"=\"aeiou\"][\"attribut2\"=\"2013-12-13\"]", value.toString());
	}

	@Test
	void testEvaluateWithXpHoehenangabe() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Baugebiet_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:hoehenangabe"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(
				"[Höhenbezug: absolutNHN|Abweichender Höhenbezug: 1|Bezugspunkt: FH|Höhe: 4|Höhe Min: 1|Höhe Max: 2|Höhe Zwingend: 3]",
				value.toString());
	}

	@Test
	void testEvaluateWithXpRasterplanBasis() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Bereich_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:rasterBasis"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(
				"[/getAttachment?featureID=XP_RASTERPLANBASIS_1&filename=B-Plan_Klingmuehl_Heideweg_Karte.tif | Externe Referenz][/getAttachment?featureID=XP_RASTERPLANBASIS_1&filename=B-Plan_Klingmuehl_Heideweg_Karte.tfw | Georeferenz]",
				value.toString());
	}

	@Test
	void testEvaluateWithXpRasterplanAenderung() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Bereich_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:rasterAenderung"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(
				"[/getAttachment?featureID=BP_RASTERPLANAENDERUNG_1&filename=B-Plan_Klingmuehl_Heideweg_Karte1.tif | Externe Referenz][/getAttachment?featureID=BP_RASTERPLANAENDERUNG_1&filename=B-Plan_Klingmuehl_Heideweg_Karte1.tfw | Georeferenz][/getAttachment?featureID=BP_RASTERPLANAENDERUNG_2&filename=B-Plan_Klingmuehl_Heideweg_Karte2.tif | Externe Referenz][/getAttachment?featureID=BP_RASTERPLANAENDERUNG_2&filename=B-Plan_Klingmuehl_Heideweg_Karte2.tfw | Georeferenz]",
				value.toString());
	}

	@Test
	void testEvaluateWithXpTextAbschnitt() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:texte"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(
				"[text1 | Das ist Textabschnitt No 1 | Externe Referenz: text1.pdf][text2 | Das ist Textabschnitt No 2 (Gesetzliche Grundlage: BGB) | Externe Referenz: text2.pdf]",
				value.toString());
	}

	@Test
	void testEvaluateWithXpGemeinde() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:gemeinde"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals("[Gemeindeschlüssel: 4011000|Regionalschlüssel: rs|Gemeinde: Bremen|Ortsteil: Whatever]",
				value.toString());
	}

	@Test
	void testEvaluateXpVerfahrensMerkmal() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(),
				new Xpath("xplan:verfahrensMerkmale"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(
				"[2013-12-13: \"so nicht\" (Schneider, nicht signiert)][2013-12-13: \"doch ok\" (Goerke, signiert)]",
				value.toString());
	}

	@Test
	void testEvaluateXpTextAbschnitte() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:texte"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(
				"[text1 | Das ist Textabschnitt No 1 | Externe Referenz: text1.pdf][text2 | Das ist Textabschnitt No 2 (Gesetzliche Grundlage: BGB) | Externe Referenz: text2.pdf]",
				value.toString());
	}

	@Test
	void testEvaluate50WithXpBegruendungAbschnitt() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_50);
		Feature feature = getTestFeature(features, "BP_VerEntsorgung");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(),
				new Xpath("xplan:refBegruendungInhalt"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals("[begruendung1 | Beg. No 1]", value.toString());
	}

	@Test
	void testEvaluate50XpTextAbschnitt() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_50);
		Feature feature = getTestFeature(features, "BP_Plan");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:texte"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(null, value);
	}

	@Test
	void testEvaluate53XpSpezExterneReferenz() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_53);
		Feature feature = getTestFeature(features, "BP_Plan");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:externeReferenz"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals("[/getAttachment?featureID=BP_Plan&filename=Satzung.pdf | Satzung]", value.toString());
	}

}
